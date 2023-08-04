package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoginAspect {
    //this is where we add all our advice related logging
    //lets start with @Before advice
    //@Before("execution(public void addAccount()")
    // @Before("execution(public void com.example.aopdemo.dao.AccountDAO.addAccount()")
    // @Before("execution(public * add *()")
    //@Before("execution(public * add*(com.example.aopdemo.Account,..))")//this is point-cut expression
    //@Before("execution(public * add*(..))")

    @Before("com.example.aopdemo.aspect.LuvAopEXpressions.forDaoPackageNoGetterAndSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        //this code run before accountDao.addAccount() method
        System.out.println(">>>>>Execute before addAccount() method execution.");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method : " + methodSignature);

        //display the method argument
        Object args[] = theJoinPoint.getArgs();
        for (Object tempArg : args) {
            System.out.println(tempArg);
            if (tempArg instanceof Account) {
                Account theAccount = (Account) tempArg;
                System.out.println("Account name : " + theAccount.getName());
                System.out.println("Account level : " + theAccount.getName());
            }
        }
    }

    //add a new advice for after returning on the fid account method
    @AfterReturning(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningAccountsAdvice(JoinPoint theJOinPoint, List<Account> result) {
        //print out which method we are advising on
        String method = theJOinPoint.getSignature().toShortString();
        System.out.println(">>>Executing after returning method : " + method);

        //print out the result
        System.out.println(">>>>Result is : " + result);

        //lets post process the data
        //convert this list to uppercase
        convertUpperCase(result);
        System.out.println(">>>>Modified Result is : " + result);
    }

    private void convertUpperCase(List<Account> result) {
        for (Account tempAccount : result) {
            String upperCase = tempAccount.getName().toUpperCase();
            tempAccount.setName(upperCase);

        }
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exception"
    )
    public void afterThrowingAccountsAdvice(JoinPoint theJointPoint, Exception exception) {
        String method = theJointPoint.getSignature().toShortString();
        System.out.println(">>>>Executing After Throwing an exception in method :  " + method);

        System.out.println(">>>>The exception is : " + exception);

    }

    @After(
            "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))"
    )
    public void afterFinallyfindAccountAdvice(JoinPoint theJointPoint) {
        String method = theJointPoint.getSignature().toShortString();
        System.out.println(">>>>Executing After finally on method :  " + method);

    }

    @Around(
            "execution(* com.example.aopdemo.service.TrafficFortuneService.getFortune(..))"
    )
    public Object aroundGetFortune(ProceedingJoinPoint theProcedingJointPoint) throws Throwable {
        //print out method we are advising
        String method = theProcedingJointPoint.getSignature().toShortString();
        System.out.println("Executing @Around on method :" + method);

        //get begin timestamp
        long begin = System.currentTimeMillis();

        //now,lets execute the method
        Object result = null;
        try {
            result = theProcedingJointPoint.proceed();
        } catch (Exception e) {
            //log the exception
            System.out.println(e.getMessage());
            //give user custom message
            //result = "Major accident! No worry";
            throw e;
        }

        //get end timestamp
        long end = System.currentTimeMillis();

        //compute duration and display it
        long ans = end - begin;
        System.out.println(">>>Duration is :" + ans / 1000.0 + " seconds");
        return result;
    }
}
