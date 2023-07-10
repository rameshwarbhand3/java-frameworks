package com.luv2code.springcoredemo.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach {

    private static Logger LOGGER = LoggerFactory.getLogger(BaseballCoach.class);

    @Override
    public String getDailyWorkOut() {
        LOGGER.info("inside controller");
        return "Spend 30 min in balling practice";
    }
}
