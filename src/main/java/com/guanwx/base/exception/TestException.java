package com.guanwx.base.exception;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/29 星期六 18:43
 * @Description:
 * @Version: 1.0
 */

public class TestException {
    public static void main(String[] args){

        test1();

    }

    // 获取余数，测试try-catch异常
    public static void test1() {
        try {
            int a = 1 % 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getClass());
        } finally {
            System.out.println("finally最后执行。。。");
        }
    }


}
