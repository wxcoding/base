package com.guanwx.base.stream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/7 星期一 13:56
 * @Description:
 * @Version: 1.0
 */

public class TestStream {
    public static void main(String[] args) {

        List<String> strings = new ArrayList<>(List.of("1", "2", "3"));
        strings.forEach(System.out::println);

        System.out.println("===================");

        HashMap<String, String> map = new HashMap<>();
        map.put("1", "1");
        map.put("2", "2");
        map.put("3", "3");
        map.forEach((key, value) -> System.out.println(key + ": " + value));

        System.out.println("-------------------");

        for (String key : map.keySet()) {
            if ("1".equals(key)) {
                map.put(key, "一");
            }
            if ("2".equals(key)) {
                map.put(key, "二");
            }
            if ("3".equals(key)) {
                map.put(key, "三");
            }
            System.out.println(key + ": " + map.get(key));
        }

        ArrayList<Integer> integers = new ArrayList<>(List.of(1, 2, 5, 6, 7));
        List<Integer> filterList = integers.stream().filter(i -> i > 3).collect(Collectors.toList());
        System.out.println(filterList);
    }


}
