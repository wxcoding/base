package com.guanwx.base.switchs;

/**
 * @Author: guanwx
 * @CreateTime: 2026/10/5 星期一 17:50
 * @Description:
 * @Version: 1.0
 */

public class Main {
    public static void main(String[] args) {
        for (ProjectEnum project : ProjectEnum.values()) {
            printProjectInfo(project);
        }
    }

    private static void printProjectInfo(ProjectEnum project) {
        System.out.println("课程：" + project.getDisplayName()
                + "，编码：" + project.getCode());

        switch (project) {
            case CHINESE:
                System.out.println("学习内容：阅读与写作");
                break;
            case MATH:
                System.out.println("学习内容：数字与逻辑");
                break;
            case ENGLISH:
                System.out.println("学习内容：词汇与语法");
                break;
            default:
                System.out.println("暂不支持该课程");
        }
    }
}
