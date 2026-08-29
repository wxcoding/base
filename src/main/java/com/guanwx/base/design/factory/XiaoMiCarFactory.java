package com.guanwx.base.design.single.factory;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/28 星期五 03:56
 * @Description:
 * @Version: 1.0
 */

public class BenChiCarFactory implements CarFactory{

    public Car getCar() {
        return new BenChiCar();
    }

}
