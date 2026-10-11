package com.guanwx.base;

import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/13 星期四 02:05
 * @Description:
 * @Version: 1.0
 */

public class Test1 {
    public static void main(String[] args) {

        int[] arr = {2, 2, 4, 4, 9, 5, 9};
//        System.out.println(Arrays.toString(bubbleSort(arr)));

//        System.out.println(Arrays.toString(twoNumsSum(arr, 5)));

//        System.out.println(reverse("guan"));

//        System.out.println(removeElements(arr, 0));

//        System.out.println(Arrays.toString(distinctNums(arr)));

//        System.out.println(replaceString("guan", "guan", "guanwx"));

//        System.out.println(distinctAndSort("22abc1ab3"));

        System.out.println(singleNumber(arr));

        System.out.println(singleNumber1(arr));

        System.out.println(generateUUID());

        System.out.println(climbStairs1(3));

    }

    // 冒泡排序
    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            System.out.println("数组为空!");
            throw new RuntimeException("数组为空!");
        }
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    // 两数之和为目标数（返回两数下标）
    public static int[] twoNumsSum(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            System.out.println("数组为空!");
            throw new RuntimeException("数组为空!");
        }
        HashMap<Integer, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            indexMap.put(nums[i], i);
            int temp = target - nums[i];
            if (indexMap.containsKey(temp)) return new int[]{i, indexMap.get(temp)};
        }
        return new int[0];
    }


    //字符串反转
    public static String reverse(String str) {
        if (!StringUtils.hasText(str)) {
            System.out.println("字符串为空!");
            throw new RuntimeException("字符串为空!");
        }
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }


    // 给你一个数组 nums 和一个值 val，原地移除所有等于 val 的元素，返回新长度
    public static int removeElements(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            System.out.println("数组为空!");
            throw new RuntimeException("数组为空!");
        }
        int newLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[newLength++] = nums[i];
            }
        }
        return newLength;
    }


    // 给定一个整数集合，进行去重
    public static int[] distinctNums(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("数组为空!");
            throw new RuntimeException("数组为空!");
        }
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int[] newNums = set.stream().mapToInt(Integer::intValue).toArray();
        return newNums;
    }


    // 字符串替换
    public static String replaceString(String str, String oldStr, String newStr) {
        if (!StringUtils.hasText(str)) {
            System.out.println("字符串为空!");
            throw new RuntimeException("字符串为空!");
        }
        return str.replace(oldStr, newStr);
    }


    // 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序
    public static int[] moveZeroes(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("数组为空!");
            throw new RuntimeException("数组为空!");
        }
        int newLength = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[newLength++] = nums[i];
            }
        }
        while (newLength < nums.length) {
            nums[newLength++] = 0;
        }
        return nums;
    }


    // 找出一个整数数组的最大数
    public static int findMax(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("数组为空!");
            throw new RuntimeException("数组为空!");
        }
        int max = nums[0];
        for (int num : nums) {
            if (num > max) {
                max = num;
            }
        }
        return max;
    }


    // 字符串去重并排序 "22abc1ab3"
    public static String distinctAndSort(String str) {
        if (!StringUtils.hasText(str)) {
            System.out.println("字符串为空!");
            throw new RuntimeException("字符串为空!");
        }
        Set<Character> set = new HashSet<>();
        char[] charArray = str.toCharArray();
        for (char c : charArray) {
            set.add(c);
        }
        String newString = set.stream().sorted().map(String::valueOf).collect(Collectors.joining());
        return newString;
    }


    // 只出现一次的数字 位运算
    public static int singleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("数组为空!");
            throw new RuntimeException("数组为空!");
        }
        int singleNum = 0;
        for (int num : nums) {
            singleNum ^= num;
        }
        return singleNum;
    }


    // 只出现一次的数字 哈希表
    public static int singleNumber1(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("数组为空!");
            throw new RuntimeException("数组为空!");
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (map.get(num) == 1) {
                return num;
            }
        }
        return -1;
    }


    // 只出现一次的数字 链表（保持插入顺序）
    public static int[] singleNumber2(int[] nums) {
        HashMap<Integer, Integer> map = new LinkedHashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        for (int num : nums) {
            if (map.get(num) == 1) {
                return new int[]{num};
            }
        }
        return new int[0];
    }


    // UUID生成
    public static String generateUUID() {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }


    // 爬楼梯：每次可以走 1 层或者 2 层台阶，求总共有多少种走法
    // 典型的动态规划问题：dp[i] 表示爬到第 i 层台阶的不同走法数量
    public static int climbStairs(int n) {
        if (n <= 0) {
            System.out.println("楼梯数必须大于0!");
            throw new RuntimeException("楼梯数必须大于0!");
        }
        // 当只有 1 层台阶时，只能走 1 步，方案数为 1
        if (n == 1) {
            return 1;
        }
        // 当有 2 层台阶时，有两种走法：1+1 或 2
        if (n == 2) {
            return 2;
        }

        // dp 数组中 dp[i] 表示爬到第 i 层的走法数
        // 到达第 i 层台阶，最后一步只能从第 i-1 层走 1 步到达，或者从第 i-2 层走 2 步到达
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        // 从第 3 层开始计算，递推公式：dp[i] = dp[i - 1] + dp[i - 2]
        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // 返回爬到第 n 层的总走法数
        return dp[n];
    }

    // 解释一下这段代码：`climbStairs1` 方法是一个递归实现的爬楼梯问题的解法。它计算从第 1 层到第 n 层的不同走法数量，每次可以选择走 1 层或 2 层台阶。
    public static int climbStairs1(int n) {
        if (n <= 0) {
            System.out.println("楼梯数必须大于0!");
            throw new RuntimeException("楼梯数必须大于0!");
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs1(n - 1) + climbStairs1(n - 2);
    }

    // 找出一个整数数组中只出现一次的数字（返回所有只出现一次的数字）
    public static int[] findSingleNumber(int[] nums) {
        if (nums == null || nums.length == 0) {
            System.out.println("数组为空!");
            throw new IllegalArgumentException("数组为空!");
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            if (map.containsKey(num)) {
                map.put(num, map.get(num) + 1);
            } else {
                map.put(num, 1);
            }
        }
        ArrayList<Integer> singleLists = new ArrayList<>();
        for (int num : nums) {
            if (map.get(num) == 1) {
                singleLists.add(num);
            }
        }
        return singleLists.stream().mapToInt(Integer::intValue).toArray();
    }



}
