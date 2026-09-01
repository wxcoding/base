package com.guanwx.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TestAop {

    @Around("execution(* com.guanwx.controller.*.*.test(..))")
    public Object method1(ProceedingJoinPoint pjp) throws Throwable {
        // 开始统计时间
        long startTime = System.nanoTime();

        // 执行方法
        Object proceed = pjp.proceed();

        // 结束统计时间
        long endTime = System.nanoTime();
        System.out.println("方法执行时间：" + pjp.toShortString() + "--------->" + (endTime - startTime) + "纳秒");

        return proceed;
    }

}
