package com.luv2code.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component //marks this annotation the class as bean class.
//@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//this tell each dependancy inject new object.

//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)//this tell each depepndancy inject same object
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In constructors : " + getClass().getSimpleName());
    }
    //define our Init method

    @PostConstruct
    public void doMyStartStuff(){
        System.out.println("In doMyStartStuff : " + getClass().getSimpleName());
    }
     //define our destroy method
    @PreDestroy
    public void doMyCleanupStuff(){
        System.out.println("In doMyCleanUpStuff : " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkOut() {
        return "Practice fast bowling for 15 mins!!!";
    }
}
