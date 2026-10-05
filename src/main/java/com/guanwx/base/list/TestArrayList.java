package com.guanwx.base.list;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

/**
 * @Author: guanwx
 * @CreateTime: 2026/10/5 星期一 12:47
 * @Description:
 * @Version: 1.0
 */

public class TestArrayList {
    public static void main(String[] args) {

        ArrayList<Object> objects = new ArrayList<>();

        // 添加不同类型的元素到 ArrayList 中
        objects.add("Java");
        objects.add("Python");
        objects.add("C++");

        TestUser user1 = new TestUser("name1", "123@com");
        TestUser user2 = new TestUser("name2", "456@com");
        objects.add(user1);
        objects.add(user2);

        System.out.println(objects);

    }
}

@Data
@AllArgsConstructor
class TestUser {
    private String name;
    private String email;
}
