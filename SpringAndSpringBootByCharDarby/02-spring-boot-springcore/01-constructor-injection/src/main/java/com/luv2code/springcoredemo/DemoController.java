package com.luv2code.springcoredemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define private field for dependancy
    private Coach myCoach;

    //define constructor  for constructor dependancy
    @Autowired  //This annotation tell spring to inject dependancy
    public DemoController(Coach theCoach) {
        this.myCoach = theCoach;     //Behind the scene, Coach theCoach = new CricketCoach();
                                      //DemoController demoController = new DemoController(theCoach);
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkOut();
    }
}



