package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest//Use when your src/main package name and src/test package are same.
@SpringBootTest(classes = MvcTestingExampleApplication.class)//use when package name are different.
public class ApplicationExampleTest {
    private static int count = 0;
    @Value("${info.app.name}")
    private String appName;
    @Value("${info.app.description}")
    private String appDescription;
    @Value("${info.app.version}")
    private String appVersion;
    @Value("${info.school.name}")
    private String schoolName;

    @Autowired
    private CollegeStudent theStudent;
    @Autowired
    private StudentGrades studentGrades;

    @BeforeEach
    void beforeEach() {
        count = count + 1;
        System.out.println("Testing: " + appName + " which is " + appDescription + " version " + appVersion + ". Execution of the test method " + count);
        theStudent.setFirstname("Ram");
        theStudent.setLastname("Bhand");
        theStudent.setEmailAddress("ram@luv2code.com");
        studentGrades.setMathGradeResults(new ArrayList<>(Arrays.asList(100.0, 80.0, 65.0, 50.0, 35.0)));
        theStudent.setStudentGrades(studentGrades);
    }

    @DisplayName("Add grade result for student grade")
    @Test
    void testAddGradeResultForStudentGrades() {
        assertEquals(330, studentGrades.addGradeResultsForSingleClass(theStudent.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Add grade result for student grades not equal")
    @Test
    void testAddGradeResultForStudentGradesNotEqual() {
        assertNotEquals(0, studentGrades.addGradeResultsForSingleClass(theStudent.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("Is greater grade")
    @Test
    void testIsGreaterGrade() {
        assertTrue(theStudent.getStudentGrades().isGradeGreater(75.0, 70.0), "failure-should be true");
    }

    @DisplayName("Is greater grade false")
    @Test
    void testIsGreaterGradeAssertFalse() {
        assertFalse(theStudent.getStudentGrades().isGradeGreater(75.0, 80.0), "failure-should be true");
    }

    @DisplayName("chack null for student grades ")
    @Test
    void testForStudentNull() {
        assertNotNull(studentGrades.checkNull(theStudent.getStudentGrades().getMathGradeResults()));
    }

    @DisplayName("find grade point average")
    @Test
    void testFindgradepointAverage(){
        assertEquals(66,studentGrades.findGradePointAverage(theStudent.getStudentGrades().getMathGradeResults()));
    }
}
