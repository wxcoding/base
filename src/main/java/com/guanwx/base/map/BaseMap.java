package com.guanwx.base.map;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class BaseMap {
    public static void main(String[] args) {

        HashMap<String, String> map = new HashMap<>();
        map.put("key1", "value1");
        map.put("key2", "value2");
        map.put("key3", "value3");
        map.put("key4", "value4");

        // map的遍历1:for-each
        map.forEach((k, v) -> System.out.println(k + " = " + v));
        log.info("==========================================================");

        // map的遍历2:for
        for (String key : map.keySet()) {
            System.out.println(key + " = " + map.get(key));
        }
        log.info("==========================================================");

        // map的遍历3:entrySet
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }
        log.info("==========================================================");

        // map的遍历4：iterator
        Iterator<Map.Entry<String, String>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            System.out.println(next.getKey() + " = " + next.getValue());
        }
    }

}
