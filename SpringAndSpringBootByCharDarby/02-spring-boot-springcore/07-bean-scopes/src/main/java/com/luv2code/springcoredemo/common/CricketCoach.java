package com.luv2code.springcoredemo.common;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component //marks this annotation the class as bean class.
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)//this tell each dependancy inject new object.

//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)//this tell each depepndancy inject same object
public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In constructors : " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkOut() {
        return "Practice fast bowling for 15 mins!!!";
    }
}
