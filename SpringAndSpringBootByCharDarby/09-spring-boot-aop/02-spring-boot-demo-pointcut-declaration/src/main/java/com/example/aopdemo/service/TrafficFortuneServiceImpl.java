package com.example.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService {

    @Override
    public String getFortune() {
        //simulate the delay
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //return a fortune

        return "Expect heavy traffic this morning";
    }

    @Override
    public String getFortune(boolean flag) {
        if(flag){
            throw new RuntimeException("Major accident...Highway closed");
        }
        return getFortune();
    }
}
