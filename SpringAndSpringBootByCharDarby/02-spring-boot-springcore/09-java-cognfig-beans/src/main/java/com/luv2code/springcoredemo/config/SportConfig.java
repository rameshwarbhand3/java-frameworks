package com.luv2code.springcoredemo.config;

import com.luv2code.springcoredemo.common.Coach;
import com.luv2code.springcoredemo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//this tell that spring container has @Bean definition methods and so spring can process the class ahe generate spring bean to be used in the application.
public class SportConfig {
    @Bean("aquatic")//it is used to explicitely declare single bean.
    public Coach swimCoach() {
        return new SwimCoach();
    }
}
