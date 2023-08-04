package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoginAspect {
    //this is where we add all our advice related logging
    //lets start with @Before advice
    //@Before("execution(public void addAccount()")
   // @Before("execution(public void com.example.aopdemo.dao.AccountDAO.addAccount()")
   // @Before("execution(public * add *()")
    //@Before("execution(public * add*(com.example.aopdemo.Account,..))")//this is point-cut expression
    //@Before("execution(public * add*(..))")
    @Before("execution(* com.example.aopdemo.dao.*.*(..))")
    public void beforeAddAccountAdvice() {
        System.out.println(">>>>>Execute before addAccount() method execution.");
        //this code run before accountDao.addAccount() method
    }

}
