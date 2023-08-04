package com.example.EmailVerification.util;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class OtpUtil {
    public String generator() {
        Random random = new Random();
        int randomNumber = random.nextInt(999999);
        String output = Integer.toString(randomNumber);
        //Below code is used for  to remain before 0 as it is ,because 001234 will be treat as 1234 which is 4 digit.
        while (output.length() < 6){
            output = "0" + output;
        }
        return output;
    }
}
