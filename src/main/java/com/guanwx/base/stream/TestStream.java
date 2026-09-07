package com.guanwx.base.stream;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/7 星期一 13:56
 * @Description:
 * @Version: 1.0
 */

public class TestStream {
    public static void main(String[] args) {

        List<String> strings = List.of("1", "2", "3");
        strings.stream().forEach(string -> System.out.println(string));

    }


}
