package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LuvAopEXpressions {
    @Pointcut("execution(* com.example.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {

    }
    //create a piontcut expression for getter method
    @Pointcut("execution(* com.example.aopdemo.dao.*.get*(..))")
    public void getter() {

    }
    //create a pointcut method for setter method
    @Pointcut("execution(* com.example.aopdemo.dao.*.set*(..))")
    public void setter() {

    }
    //create a combine pointcut include package nad exclude getter and setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterAndSetter(){

    }
}
