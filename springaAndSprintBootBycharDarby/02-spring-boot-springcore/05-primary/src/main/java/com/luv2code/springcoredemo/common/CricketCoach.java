package com.luv2code.springcoredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component //marks this annotation the class as bean class.
@Primary //This annotation tell choose this as a primary bean class by default.
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkOut() {
        return "Practice fast bowling for 15 mins!!!";
    }
}
