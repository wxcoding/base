package com.guanwx.base.io;

import org.apache.tomcat.websocket.AsyncChannelWrapper;

import java.io.*;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/6 星期日 10:48
 * @Description:
 * @Version: 1.0
 */

public class TestIO {

    private final static String filePath = "C:\\Users\\Administrator\\Desktop\\IO\\test.txt";

    public static void main(String[] args) throws FileNotFoundException {

        readFile (filePath);

        System.out.println("--------------------");

        writeFile(filePath, "Hello World!" + System.lineSeparator());

        readFile (filePath);


    }

    /**
     * 读取文件内容
     * @param filePath 文件路径
     */
    public static void readFile (String filePath) {
        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader)) {
            String s;
            while ((s = bufferedReader.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 写入文件内容
     * @param filePath 文件路径
     * @param content 写入内容
     */
    public static void writeFile (String filePath, String content) {
        // FileOutputStream 第二个参数 true 表示追加而非覆盖
        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath, true);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
             BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter)) {
            bufferedWriter.write(content);
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


}
/**
 *
 * ## 一、先搞懂最底层概念（必须先看）
 *
 * ### 1. 什么是 IO
 *
 * IO = Input/Output，输入输出，**数据在内存和外部设备（磁盘、网络、控制台）之间传输**。
 *
 * - 输入：外部 → JVM 内存（读 read）
 * - 输出：JVM 内存 → 外部（写 write）
 *
 * >
 * > 核心本质：IO 操作**慢于 CPU**，大部分时间线程在等待数据。
 *
 * ### 2. 字节 vs 字符（90% 初学者踩坑点）
 *
 * 1. **字节 (Byte)**：原始二进制数据，一切文件（图片、视频、exe、txt）都可以字节读取
 *    - 字节流：以 `byte` 为单位，一次读 1 字节。抽象顶层父类：`InputStream / OutputStream`
 * 2. **字符 (Char)**：人类看得懂的文字。字符 = 字节 + 编码 (UTF‑8/GBK)
 *    - 字符流：以 `char` 为单位。抽象顶层父类：`Reader / Writer`
 *
 * >
 * > 口诀：**复制文件、图片视频用字节流；读写文本文件优先字符流**
 *
 * ## 二、传统 IO (BIO) 体系结构图（熟记）
 *
 * Java IO 分为四大抽象根类，所有流都继承它们：
 *
 * 1. `InputStream` 字节输入（读）
 * 2. `OutputStream` 字节输出（写）
 * 3. `Reader` 字符输入（读）
 * 4. `Writer` 字符输出（写）
 *
 * ### 字节流常用实现类
 *
 * 表格
 *
 * | 类 | 作用 |
 * | --- | --- |
 * | FileInputStream / FileOutputStream | 文件字节流，直接操作磁盘文件 |
 * | BufferedInputStream / BufferedOutputStream | **缓冲字节流，带缓冲区，效率高** |
 * | ObjectInputStream / ObjectOutputStream | 对象序列化流，读写 Java 对象 |
 * | ByteArrayInputStream / ByteArrayOutputStream | 内存数组流，不操作磁盘 |
 *
 * ### 字符流常用实现类
 *
 * 表格
 *
 * | 类 | 作用 |
 * | --- | --- |
 * | FileReader / FileWriter | 文件字符流 |
 * | BufferedReader / BufferedWriter | 缓冲字符流，`readLine()` 读取一行，最常用！ |
 * | InputStreamReader / OutputStreamWriter | **转换流！字节→字符的桥梁，可以指定编码，重中之重！** |
 *
 * ## 三、核心设计模式：装饰器模式（看懂为什么 new 流一层套一层）
 *
 * 你经常看见这种代码：
 *
 * ```
 * BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("a.txt"),"UTF-8"));
 * ```
 *
 * 很多人疑惑为什么要层层包裹：
 *
 * >
 * > FileInputStream：节点流，直接对接数据源（文件）
 * > InputStreamReader：转换流（字节转字符）
 * > BufferedReader：装饰流，给上层**增加缓冲功能**，提升速度，新增 readLine () 方法
 *
 * - **节点流**：最内层，直接对接数据源（文件、数组）
 * - **处理流 (装饰流)**：套在外层，增强功能（缓冲、序列化、编码），**关闭流只需关最外层即可**
 *
 * ## 四、基础实操练习（照着敲一遍，立刻理解）
 *
 * ### 练习 1：字节流读取文件（无缓冲，效率低）
 *
 * ```
 * import java.io.FileInputStream;
 * import java.io.IOException;
 *
 * public class IoDemo {
 *     public static void main(String[] args) {
 *         FileInputStream fis = null;
 *         try {
 *             fis = new FileInputStream("test.txt");
 *             int data;
 *             // read() 返回读取到的字节；读到末尾返回 -1
 *             while((data = fis.read()) != -1){
 *                 System.out.print((char)data);
 *             }
 *         } catch (IOException e) {
 *             e.printStackTrace();
 *         }finally {
 *             // finally 保证流关闭！释放资源
 *             if(fis != null){
 *                 try {
 *                     fis.close();
 *                 } catch (IOException e) {
 *                     e.printStackTrace();
 *                 }
 *             }
 *         }
 *     }
 * }
 * ```
 *
 * >
 * > JDK7+ 推荐使用 **try‑with‑resources**，自动关闭流，不用手写 finally
 *
 * ```
 * try(FileInputStream fis = new FileInputStream("test.txt")){
 *     int b;
 *     while ((b = fis.read())!=-1){
 *         System.out.print((char)b);
 *     }
 * }catch (IOException e){
 *     e.printStackTrace();
 * }
 * ```
 *
 * >
 * > try‑with‑resources 是 IO 必须掌握的语法！
 *
 * ### 练习 2：带缓冲区复制文件（推荐方式）
 *
 * ```
 * import java.io.BufferedInputStream;
 * import java.io.BufferedOutputStream;
 * import java.io.FileInputStream;
 * import java.io.FileOutputStream;
 *
 * public class CopyDemo {
 *     public static void main(String[] args) {
 *         try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream("source.jpg"));
 *              BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("target.jpg"))) {
 *             byte[] buffer = new byte[1024]; // 一次读取1KB
 *             int len;
 *             while ((len = bis.read(buffer)) != -1) {
 *                 bos.write(buffer, 0, len);
 *             }
 *         } catch (Exception e) {
 *             e.printStackTrace();
 *         }
 *     }
 * }
 * ```
 *
 * ### 练习 3：字符缓冲流读取文本一行
 *
 * ```
 * try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("test.txt"),"UTF-8"))){
 *     String line;
 *     while ((line = br.readLine()) != null){
 *         System.out.println(line);
 *     }
 * }catch (IOException e){
 *     e.printStackTrace();
 * }
 * ```
 *
 * >
 * > FileReader 坑点：不能指定编码！生产环境不要直接用 FileReader，优先 `InputStreamReader` 指定 UTF‑8，防止中文乱码！
 *
 * ## 五、乱码问题底层原理（高频面试）
 *
 * 1. 文件保存编码 UTF‑8，程序读取用 GBK 解码 → 乱码
 * 2. FileReader 使用**操作系统默认编码**，跨平台就乱码。
 * ✅ 解决方案：永远使用转换流 `InputStreamReader(流,"UTF-8")` 显式指定编码。
 *
 * ## 六、BIO / NIO / AIO 的区别（IO 进阶，面试必问）
 *
 * 传统`InputStream/Reader`属于 **BIO (阻塞 IO)**
 *
 * 1. **BIO 阻塞 IO (java.io 包)**
 *    - 线程发起 read，没有数据时线程**阻塞等待**，不能干别的事
 *    - 一请求一线程，高并发网络场景性能差，适合简单文件读写
 * 2. **NIO 非阻塞 IO (java.nio 包 JDK1.4)**
 *    - 三大组件：Buffer 缓冲区、Channel 通道、Selector 选择器
 *    - 线程可以轮询看数据有没有就绪，不用死等，一个线程处理多个连接，网络高并发首选
 * 3. **AIO 异步非阻塞 IO (JDK1.7 NIO.2)**
 *    - 发起读写直接返回，操作系统读完后通知线程回调
 *
 * >
 * > 学习顺序：先吃透 java.io (BIO) → 再学 NIO。不要一上来就啃 NIO，容易放弃。
 *
 * ## 七、学习步骤清单（执行路线）
 *
 * 1. 分清字节流 / 字符流区别，背会 4 大父类
 * 2. 理解节点流、装饰流，看懂嵌套 new 流的含义
 * 3. 练习 5 个基础案例：读文件、写文件、复制文件、读一行文本、指定编码读写
 * 4. 掌握 try‑with‑resources 自动关闭流，明白为什么必须关闭流（释放操作系统文件句柄资源）
 * 5. 理解缓冲区作用：减少磁盘 IO 次数，为什么带 Buffered 更快
 * 6. 学习序列化流 ObjectOutputStream，transient 关键字
 * 7. 学习 File 类（文件和目录操作，不属于流，但是 IO 配套工具）
 * 8. 进阶：NIO Buffer、Channel、Selector
 * 9. 面试题复盘：BIO/NIO/AIO 区别、字节字符区别、缓冲区原理、装饰器模式、乱码原因
 *
 * ## 八、高频坑点总结
 *
 * 1. read () 返回 int！不是 byte，返回‑1 代表读到末尾
 * 2. 使用缓冲输出流，数据可能存在缓冲区没刷入磁盘，`flush()`刷新；close () 也会自动 flush
 * 3. 流用完必须关闭；try‑with‑resources 自动关闭
 * 4. FileReader 不能指定编码，不要在项目中使用
 * 5. 复制文本文件可以用字符流；**图片、视频、压缩包绝对不能用字符流，会损坏文件！**
 *
 *
 */
