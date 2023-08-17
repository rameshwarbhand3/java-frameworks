package com.luv2code.springmvc;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repository.HistoryGradeDao;
import com.luv2code.springmvc.repository.MathGradeDao;
import com.luv2code.springmvc.repository.ScienceGradeDao;
import com.luv2code.springmvc.repository.StudentDao;
import com.luv2code.springmvc.service.StudentAndGradeService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@TestPropertySource("/application.properties")//to read h2 database In memory properties from file
@SpringBootTest
public class StudentAndGradesServiceTest {
    @Autowired
    private JdbcTemplate jdbc;
    @Autowired
    private StudentAndGradeService studentAndGradeService;
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private MathGradeDao mathGradeDao;
    @Autowired
    private ScienceGradeDao scienceGradeDao;
    @Autowired
    private HistoryGradeDao historyGradeDao;


    @BeforeEach
    void setUpDatabase() {
        jdbc.execute("insert into student (id,firstname,lastname,email_address) values(1,'ram','Bhand','ram@luv2code.com')");
        jdbc.execute("insert into math_grade(id,student_id,grade)values(1,1,50.0)");
        jdbc.execute("insert into science_grade(id,student_id,grade)values(1,1,50.0)");
        jdbc.execute("insert into history_grade(id,student_id,grade)values(1,1,50.0)");


    }

    @Test
    void createStudentService() {
        //studentAndGradeService.createStudent("Ram", "Bhand", "ram@luv2code.com");
        CollegeStudent collegeStudent = studentDao.findByEmailAddress("ram@luv2code.com");
        assertEquals("ram@luv2code.com", collegeStudent.getEmailAddress(), "find by email");

    }

    @Test
    void isStudentNullCheck() {
        assertTrue(studentAndGradeService.checkIfStudentIsNull(1));
        assertFalse(studentAndGradeService.checkIfStudentIsNull(0));
    }

    @Test
    void deleteStudentService() {
        Optional<CollegeStudent> deletedCollegeStudent = studentDao.findById(1);
        Optional<MathGrade> deletedMathGrade = mathGradeDao.findById(1);
        Optional<ScienceGrade> deletedScienceGrade = scienceGradeDao.findById(1);
        Optional<HistoryGrade> deletedHistoryGrade = historyGradeDao.findById(1);

        assertTrue(deletedCollegeStudent.isPresent(), "Return true");
        assertTrue(deletedMathGrade.isPresent());
        assertTrue(deletedScienceGrade.isPresent());
        assertTrue(deletedHistoryGrade.isPresent());

        studentAndGradeService.deleteStudent(1);

        deletedCollegeStudent = studentDao.findById(1);
        deletedMathGrade = mathGradeDao.findById(1);
        deletedScienceGrade = scienceGradeDao.findById(1);
        deletedHistoryGrade = historyGradeDao.findById(1);

        assertFalse(deletedCollegeStudent.isPresent(), "Return false");
        assertFalse(deletedMathGrade.isPresent());
        assertFalse(deletedScienceGrade.isPresent());
        assertFalse(deletedHistoryGrade.isPresent());
    }

    @Sql("/insertData.sql")
    @Test
    void getGradeBookService() {
        Iterable<CollegeStudent> studentLists = studentAndGradeService.getGradeBook();
        List<CollegeStudent> list = new ArrayList<>();
        for (CollegeStudent studentList : studentLists) {
            list.add(studentList);
        }
        assertEquals(5, list.size());
    }

    @Test
    void createGradeService() {
        //create the grade
        assertTrue(studentAndGradeService.createGrade(80.5, 1, "math"));
        assertTrue(studentAndGradeService.createGrade(80.5, 1, "science"));
        assertTrue(studentAndGradeService.createGrade(80.5, 1, "history"));

        //Get all grades with studentId
        Iterable<MathGrade> mathGrades = mathGradeDao.findGradeByStudentId(1);
        Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findGradeByStudentId(1);
        Iterable<HistoryGrade> historyGrades = historyGradeDao.findGradeByStudentId(1);

        //verify there is grade
        assertTrue(mathGrades.iterator().hasNext(), "student has math grades");
        assertTrue(scienceGrades.iterator().hasNext(), "student has science grades");
        assertTrue(historyGrades.iterator().hasNext(), "student has history grades");
    }

    @Test
    void createGradeServiceReturnFalse() {
        assertFalse(studentAndGradeService.createGrade(105, 1, "math"));
        assertFalse(studentAndGradeService.createGrade(-5, 1, "math"));
        assertFalse(studentAndGradeService.createGrade(10, 2, "math"));
        assertFalse(studentAndGradeService.createGrade(10, 1, "literature"));
        assertFalse(studentAndGradeService.createGrade(10, 0, "math"));

    }
    @Test
    void deleteGradeService(){
        assertEquals(1,studentAndGradeService.deleteGrade(1,"math"),"Return student id after delete");
        assertEquals(1,studentAndGradeService.deleteGrade(1,"science"),"Return student id after delete");
        assertEquals(1,studentAndGradeService.deleteGrade(1,"history"),"Return student id after delete");
    }

    @Test//if grade id and grade type are  invalid
    void deleteGradeServiceReturnStudentIdOfZero(){
        assertEquals(0,studentAndGradeService.deleteGrade(0,"math"),"Grade id should not equal to zero");
        assertEquals(0,studentAndGradeService.deleteGrade(1,"literature"),"No student should have a literature class");
    }
    @Test
    void getStudentInformation(){
        GradebookCollegeStudent gradebookCollegeStudent = studentAndGradeService.getStudentInformation(1);
        assertNotNull(gradebookCollegeStudent);
        assertEquals(1,gradebookCollegeStudent.getId());
        assertEquals("ram",gradebookCollegeStudent.getFirstname());
        assertEquals("Bhand",gradebookCollegeStudent.getLastname());
        assertEquals("ram@luv2code.com",gradebookCollegeStudent.getEmailAddress());
        assertEquals("ram Bhand",gradebookCollegeStudent.getFullName());
        assertTrue(gradebookCollegeStudent.getStudentGrades().getMathGradeResults().size()==1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getScienceGradeResults().size()==1);
        assertTrue(gradebookCollegeStudent.getStudentGrades().getHistoryGradeResults().size()==1);
    }
    @Test
    void getStudentInformationReturnNull(){
        GradebookCollegeStudent gradebookCollegeStudent = studentAndGradeService.getStudentInformation(0);
        assertNull(gradebookCollegeStudent);
    }

    @AfterEach
    void setUpAfterTransaction() {
        jdbc.execute("DELETE FROM Student");
        jdbc.execute("DELETE FROM math_grade");
        jdbc.execute("DELETE FROM science_grade");
        jdbc.execute("DELETE FROM history_grade");
    }
}
