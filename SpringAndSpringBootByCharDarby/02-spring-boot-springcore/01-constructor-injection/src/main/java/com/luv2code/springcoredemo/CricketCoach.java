package com.luv2code.springcoredemo;

import org.springframework.stereotype.Component;

@Component //marks this annotation the class as bean class.
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkOut() {
        return "Practice fast bowling for 15 mins!!!";
    }
}
