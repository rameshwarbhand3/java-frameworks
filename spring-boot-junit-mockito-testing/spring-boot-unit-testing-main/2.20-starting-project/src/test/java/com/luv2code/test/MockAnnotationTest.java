package com.luv2code.test;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
public class MockAnnotationTest {
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    CollegeStudent studentOne;
    @Autowired
    StudentGrades studentGrades;

    //@Mock
    @MockBean
    private ApplicationDao applicationDao;

    //@InjectMocks
    @Autowired
    private ApplicationService applicationService;


    @BeforeEach
    void setUp() {
        studentOne.setFirstname("Ram");
        studentOne.setLastname("Bhand");
        studentOne.setEmailAddress("ram@lu2code.com");
        studentOne.setStudentGrades(studentGrades);
    }

    @DisplayName("When & verify")
    @Test
    void testAddGrades() {
        when(applicationDao.addGradeResultsForSingleClass(
                studentGrades.getMathGradeResults())).thenReturn(100.0);

        assertEquals(100.0, applicationService.addGradeResultsForSingleClass(
                studentOne.getStudentGrades().getMathGradeResults()));

        verify(applicationDao).addGradeResultsForSingleClass(studentGrades.getMathGradeResults());

    }

    @DisplayName("Find Gpa")
    @Test
    void testFindGpa(){
        when(applicationDao.findGradePointAverage(studentGrades.getMathGradeResults())).thenReturn(40.0);
        assertEquals(40.0,applicationService.findGradePointAverage(studentOne.getStudentGrades().getMathGradeResults()));
        verify(applicationDao).findGradePointAverage(studentGrades.getMathGradeResults());
    }

    @DisplayName("Not Null")
    @Test
    void testNotNull(){
        when(applicationDao.checkNull(
                studentGrades.getMathGradeResults())).thenReturn(true);

        assertNotNull(applicationService.checkNull(
                studentOne.getStudentGrades().getMathGradeResults()));

        verify(applicationDao).checkNull(studentGrades.getMathGradeResults());
    }
    @DisplayName("Throw runtime Exception")
    @Test
    void testThrowsException(){
        CollegeStudent nullStudent = (CollegeStudent) applicationContext.getBean("collegeStudent");
        //doThrow(new RuntimeException()).when(applicationDao.checkNull(nullStudent));
        when(applicationDao.checkNull(nullStudent)).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> applicationService.checkNull(nullStudent));
        verify(applicationDao,times(1)).checkNull(nullStudent);
    }

    @DisplayName("Multiple stubbing")
    @Test
    public void testStubbingMultipleCalls(){
        CollegeStudent nullStudent = (CollegeStudent) applicationContext.getBean("collegeStudent");
        when(applicationDao.checkNull(nullStudent)).thenThrow(new RuntimeException()).thenReturn("Do not throws exception second time");
        assertThrows(RuntimeException.class,()->{
            applicationService.checkNull(nullStudent);
        });
        assertEquals("Do not throws exception second time",applicationService.checkNull(nullStudent));
        verify(applicationDao,times(2)).checkNull(nullStudent);
    }

}
