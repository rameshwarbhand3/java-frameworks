package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component //marks this annotation the class as bean class.

public class CricketCoach implements Coach {
    public CricketCoach() {
        System.out.println("In constructors : " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkOut() {
        return "Practice fast bowling for 15 mins!!!";
    }
}
