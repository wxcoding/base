package com.guanwx.base.design.factory;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/28 星期五 04:04
 * @Description:
 * @Version: 1.0
 */

public class Main {
    public static void main(String[] args) {

        CarFactory carFactory1 = new XiaoMiCarFactory();
        Car car1 = carFactory1.getCar();
        System.out.println(car1.getName());

        CarFactory carFactory2 = new BenChiCarFactory();
        Car car2 = carFactory2.getCar();
        System.out.println(car2.getName());


    }


}
