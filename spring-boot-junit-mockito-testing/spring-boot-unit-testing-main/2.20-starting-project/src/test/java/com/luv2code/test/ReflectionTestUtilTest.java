package com.luv2code.test;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.ReflectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@SpringBootTest(classes = ReflectionTestUtilTest.class)
public class ReflectionTestUtilTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    CollegeStudent studentOne;
    @Autowired
    StudentGrades studentGrades;

    @BeforeEach
    public void studentBeforeEach(){
        studentOne.setFirstname("Ram");
        studentOne.setLastname("Bhand");
        studentOne.setEmailAddress("ram@luv2code.com");
        studentOne.setStudentGrades(studentGrades);
        ReflectionTestUtils.setField(studentOne,"id",1);
        ReflectionTestUtils.setField(studentOne,"studentGrades",new StudentGrades(new ArrayList<>(Arrays.asList(100.0,85.0,76.5,91.75))));

    }
    //If we don't have getter and setter and want to access private field in your test we use above approach
//    @Test
//    void getPrivateField(){
//        assertEquals(1,ReflectionTestUtils.getField(studentOne,"id"));
//    }

    @Test
    void getPrivateMethod(){
        assertEquals("Ram 1",ReflectionTestUtils.invokeMethod(studentOne,"getFirstNameId"),"Fail private method not call");

    }

}
