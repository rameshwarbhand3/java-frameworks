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
    private Coach anotherCoach;

    //define constructor  for constructor dependancy
    @Autowired  //This annotation tell spring to inject dependancy
    public DemoController(@Qualifier("cricketCoach") Coach theCoach, @Qualifier("cricketCoach") Coach theAnotherCoach) {         //Coach theCoach = new CricketCoach();
        //System.out.println("In constructors : " + getClass().getSimpleName());
        myCoach = theCoach;                       //Coach theCoach = new CricketCoach();
        anotherCoach = theAnotherCoach;           //DemoController demo = new DemoController(theCoach);
    }

    //for singleton scope both injections refer to same cricketCoach object.
    //for prototype scope each injection refer to new instance of cricketCoach object.
    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkOut();
    }

    @GetMapping("/check")
    public String check() {
        return "Comparing bean : coach==anotherCoach, " + (myCoach == anotherCoach);
    }
}



