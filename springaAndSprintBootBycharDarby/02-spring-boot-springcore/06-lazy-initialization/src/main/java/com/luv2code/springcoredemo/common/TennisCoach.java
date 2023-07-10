package com.luv2code.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component

public class TennisCoach implements Coach {
    public TennisCoach() {
        System.out.println("In constructors " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkOut() {
        return "Practice your backend volley";
    }
}

