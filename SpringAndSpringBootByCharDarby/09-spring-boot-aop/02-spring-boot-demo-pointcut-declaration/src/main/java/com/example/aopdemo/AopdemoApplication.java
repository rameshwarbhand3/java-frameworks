package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MemberShipDAO;
import com.example.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDao, MemberShipDAO theMemberShipDao,
                                               TrafficFortuneService theTrafficFortuneService) {
        return runner -> {
           // System.out.println("Hello world");
            //demoTheBeforeAdvice(theAccountDao,theMemberShipDao);
            //demoTheAfterReturnAdvice(theAccountDao);
            //demoTheAfterThrowingAdvice(theAccountDao);
           // demoTheAfterAdvice(theAccountDao);
            //demoTheAroundAdvice(theTrafficFortuneService);
            //demoTheAroundAdviceHandleException(theTrafficFortuneService);
            demoTheAroundAdviceRethroweException(theTrafficFortuneService);
        };
    }

    private void demoTheAroundAdviceRethroweException(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("Main program : demoTheAroundAdviceRethroweException");
        System.out.println("Calling getFortune");
        boolean flag = true;
        String data = theTrafficFortuneService.getFortune(flag);
        System.out.println("-------------------------------------------------");
        System.out.println("My fortune  is : "+data);
        System.out.println("finished");
    }

    private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("Main program : demoTheAroundAdviceHandleException");
        System.out.println("Calling getFortune");
        boolean flag = true;
        String data = theTrafficFortuneService.getFortune(flag);
        System.out.println("-------------------------------------------------");
        System.out.println("My fortune  is : "+data);
        System.out.println("finished");
    }


    private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("Main program : demoTheAroundAdvice");
        System.out.println("Calling getFortune");
        String data = theTrafficFortuneService.getFortune();
        System.out.println("-------------------------------------------------");
        System.out.println("My fortune  is : "+data);
        System.out.println("finished");
    }


    private void demoTheAfterAdvice(AccountDAO theAccountDao) {
        List<Account>theAccounts=null;
        try {
            boolean flag = false;
            theAccounts = theAccountDao.findAccounts(flag);
        }catch (Exception exc){
            System.out.println("Main program : caught the exception "+exc);
        }
        //display the account
        //System.out.println("Main program : demoTheReturnAfterAdvice");
        // System.out.println(theAccounts);
    }

    private void demoTheAfterThrowingAdvice(AccountDAO theAccountDao) {
        List<Account>theAccounts=null;
        try {
            boolean flag = true;
            theAccounts = theAccountDao.findAccounts(flag);
        }catch (Exception exc){
            System.out.println("Main program : caught the exception "+exc);
        }
        //display the account
        //System.out.println("Main program : demoTheReturnAfterAdvice");
       // System.out.println(theAccounts);

    }

    private void demoTheAfterReturnAdvice(AccountDAO theAccountDao) {

        List<Account>theAccounts = theAccountDao.findAccounts();
        //display the account
      // System.out.println("Main program : demoTheReturnAfterAdvice");
      //System.out.println(theAccounts);
       //System.out.println("/n");
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDao, MemberShipDAO theMemberShipDao) {
        //call the accountDao business method
        Account theAccount = new Account();
        theAccount.setName("Madhu");
        theAccount.setLevel("Platinum");
        theAccountDao.addAccount(theAccount,true);
        theAccountDao.doWork();

        //call accountDao getter/setter method
        theAccountDao.setName("FooBar");
        theAccountDao.setServiceCode("silver");

        String name = theAccountDao.getName();
        String code = theAccountDao.getServiceCode();

        //call the membershipDao business method
       theMemberShipDao.addSillyMember();
        theMemberShipDao.goToSleep();
    }

}
