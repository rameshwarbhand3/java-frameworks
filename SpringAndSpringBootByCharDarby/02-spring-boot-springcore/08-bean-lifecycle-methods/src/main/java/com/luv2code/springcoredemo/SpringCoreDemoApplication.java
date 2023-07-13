package com.luv2code.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//If application file in different packages then we have to add explicitely below
/*@SpringBootApplication(
	scanBasePackages = {"com.luv2code.springcoredemo","com.luv2code.util"}
)*/

@SpringBootApplication
public class SpringCoreDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCoreDemoApplication.class, args);
    }

}
