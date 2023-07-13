package com.luv2code.springcoredemo.common;
//Not giving @Component anotation
public class SwimCoach implements Coach {
    public SwimCoach() {
        System.out.println("In constructors : " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkOut() {
        return "swim 1000 meter as a warm up";
    }
}
