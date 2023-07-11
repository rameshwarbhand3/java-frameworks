package com.luv2code.springcoredemo.rest;

import com.luv2code.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    //define private field for dependancy
    private Coach myCoach;


    //define constructor  for constructor dependancy
    @Autowired  //This annotation tell spring to inject dependancy
    public DemoController(@Qualifier("cricketCoach") Coach theCoach) {         ;
        //System.out.println("In constructors : " + getClass().getSimpleName());
        myCoach = theCoach;                       //Coach theCoach = new CricketCoach();
                                                  //DemoController demo = new DemoController(theCoach);
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkOut();
    }


}



