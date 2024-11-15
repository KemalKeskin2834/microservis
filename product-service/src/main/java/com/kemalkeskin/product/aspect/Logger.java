package com.kemalkeskin.product.aspect;
import java.util.logging.Logger.*;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {

    private java.util.logging.Logger logger= java.util.logging.Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.kemalkeskin.product.business.*.*(..))")
    private void getServiceLayer(){

    }

    @Pointcut("execution(* com.kemalkeskin.product.repository.*.*(..))")
    private void getRepositoryLayer(){

    }

    @Pointcut("execution(* com.kemalkeskin.product.controller.*.*(..))")
    private void getControllerLayer(){

    }

    @Pointcut("getServiceLayer() ||getRepositoryLayer() || getControllerLayer()  ")
    private void allLayers(){

    }

    @Before("allLayers()")
    public void logg(JoinPoint joinPoint) {
        String message=joinPoint.getSignature().toShortString();
        logger.info("@Before method: "+message);
        Object[] args=joinPoint.getArgs();
        for (Object arg :args)
            System.out.println("argument: "+arg);

    }
    @AfterReturning(pointcut="allLayers()",returning="result")
    public void  afterReturning(JoinPoint joinPoint,Object result){
        String method=joinPoint.getSignature().toShortString();
        logger.info("@AfterReturning: calling from method: "+method);
        logger.info("result: "+result);

    }


}
