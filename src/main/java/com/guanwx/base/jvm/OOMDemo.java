package com.guanwx.base.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/10 星期四 03:07
 * @Description:
 * @Version: 1.0
 */

public class OOMDemo {

    // JVM启动参数设置小堆，方便复现
    // -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
    public static void main(String[] args) {
        List<byte[]> list = new ArrayList<>();
        int count = 0;
        while(true){
            list.add(new byte[1024 * 1024]); // 1MB数组
            System.out.println("分配第" + (++count) + "MB");
        }
    }


}
