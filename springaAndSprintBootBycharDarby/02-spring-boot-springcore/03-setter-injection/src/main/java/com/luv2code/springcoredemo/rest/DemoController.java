package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define private field for dependancy
    private Coach myCoach;

    //define constructor  for constructor dependancy
    @Autowired  //This annotation tell spring to inject dependancy
   public void setCoach(Coach theCoach){         //Coach theCoach = new CricketCoach();
        myCoach = theCoach;                       //DemoController demo = new DemoController();
    }                                              // demo.setCoach(theCoach);

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkOut();
    }
}



