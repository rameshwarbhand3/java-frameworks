package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MemberShipDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDao, MemberShipDAO theMemberShipDao) {
        return runner -> {
           // System.out.println("Hello world");
            demoTheBeforeAdvice(theAccountDao,theMemberShipDao);
        };
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDao, MemberShipDAO theMemberShipDao) {
        //call the business method
        Account theAccount = new Account();
        theAccountDao.addAccount(theAccount,true);
        theAccountDao.doWork();

       theMemberShipDao.addSillyMember();
        theMemberShipDao.goToSleep();
    }

}
