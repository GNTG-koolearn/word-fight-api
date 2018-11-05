package com.koolearn.wordfight.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class ServiceAspect {

    @Pointcut("execution(public * com.koolearn.wordfight.service.*.*(..))")
    public void log(){}
    
    @Before("log()")
    public void exBefore(JoinPoint joinPoint){
        log.info("class method:{}.{}",joinPoint.getSignature().getDeclaringTypeName(),joinPoint.getSignature().getName());
        log.info("args:{}",JSON.toJSONString(joinPoint.getArgs()));
    }
    
    @AfterReturning(returning="result",pointcut="log()")
    public void exAfterReturning(Object result){
        log.info("执行返回值：{}",result);
    }
}
