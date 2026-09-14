package com.guanwx.base.list;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/15 星期二 00:32
 * @Description:
 * @Version: 1.0
 */

public class TestList {
    public static void main(String[] args) {

        List<String> list1 = new ArrayList<>(List.of("一", "二", "三", "四"));
        System.out.println(list1);
        list1.remove("三");
        System.out.println(list1);

        System.out.println("-------------------");

        List<String> list = List.of("Hello", "World", "Java", "List");
        System.out.println(list);
        list.remove("Java"); //List.of() 是不可变集合，不支持增删操作，这里会抛出 UnsupportedOperationException 异常
        System.out.println(list);



    }
}
