package com.guanwx.base.string;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
public class BaseString {
    public static void main(String[] args) {

//        System.out.println(stringLowOrUpper("GUA000NW9887X"));
//        System.out.println(stringLowOrUpper("gUANWX"));
//
//        List<String> list = Arrays.asList("Java", "Python", "C++", "C#");
//        log.info("list: {}", list);
//        mapTest(list).forEach((k, v) -> System.out.println(k + " : " + v));

//        int[] ints = towSum1(new int[]{2, 7, 11, 15}, 17);
//        System.out.println(Arrays.toString(ints));
        System.out.printf(listDistinct2(Arrays.asList(1, 2, 2, 3, 4, 5, 6, 6, 7, 8, 9, 9, 10)).toString());

//        System.out.printf(reverse("abcdefg"));

//        System.out.printf(reverse2("abcdefg"));

//        Scanner scanner = new Scanner(System.in);
//        String next = scanner.nextLine();
//        System.out.printf(reverse2(next));
    }

    // 判断字符串首字母是否为大写，如果是大写转换为小写，如果是大写转换为小写
    public static String stringLowOrUpper(String str) {
        if (!StringUtils.hasText(str)) {
            return "字符串为空!";
        }
        if (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z') {
            return str.toLowerCase();
        } else {
            return str.toUpperCase();
        }
    }

    public static HashMap<String, List<String>> mapTest(List<String> list) {
        HashMap<String, List<String>> hashMap = new HashMap<>();
        hashMap.put("第一列表",list);
        hashMap.put("第二列表",list);
        return hashMap;
    }

    // 计算两数之和为指定的一个数，找出那两个数的下标
    public static int[] towSum1(int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j ++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    // 给定一个整数集合，进行去重
    public static List<Integer> listDistinct(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.stream().distinct().collect(Collectors.toList());
    }

    public static List<Integer> listDistinct2(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        List<Integer> arrayList = new ArrayList<>(new HashSet<>(list));
//        set.addAll(list);
//        for (int i = 0; i < list.size(); i++) {
//            if (!set.contains(list.get(i))) {
//                set.add(list.get(i));
//            }
//            set.add(list.get(i));
//        }
        return arrayList;
    }

    // 给定一个字符串，进行反转
    public static String reverse(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        return new StringBuilder(str).reverse().toString();
    }

    // 给定一个字符串，进行反转2
    public static String reverse2(String str) {
        if (str == null || str.isEmpty()) {
            return "";
        }
        StringBuilder newString = new StringBuilder();
        for (int i = str.length() - 1; i > -1; i--) {
            newString.append(str.charAt(i));
        }
        return newString.toString();
    }


}
