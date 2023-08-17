package com.luv2code.springmvc.service;

import com.luv2code.springmvc.models.*;
import com.luv2code.springmvc.repository.HistoryGradeDao;
import com.luv2code.springmvc.repository.MathGradeDao;
import com.luv2code.springmvc.repository.ScienceGradeDao;
import com.luv2code.springmvc.repository.StudentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

@Service
@Transactional
public class StudentAndGradeService {
    @Autowired
    private StudentDao studentDao;
    @Autowired
    private MathGradeDao mathGradeDao;
    @Autowired
    private MathGrade mathGrade;
    @Autowired
    private ScienceGrade scienceGrade;
    @Autowired
    private ScienceGradeDao scienceGradeDao;
    @Autowired
    private HistoryGradeDao historyGradeDao;
    @Autowired
    private HistoryGrade historyGrade;

    public void createStudent(String firstname, String lastname, String emailAddress) {
        CollegeStudent theStudent = new CollegeStudent(firstname, lastname, emailAddress);
        theStudent.setId(0);
        studentDao.save(theStudent);
    }

    public boolean checkIfStudentIsNull(int id) {
        Optional<CollegeStudent> student = studentDao.findById(id);
        if (student.isPresent()) {
            return true;
        }
        return false;
    }

    public void deleteStudent(int id) {
        if (checkIfStudentIsNull(id)) {
            studentDao.deleteById(id);
            mathGradeDao.deleteById(id);
            scienceGradeDao.deleteById(id);
            historyGradeDao.deleteById(id);
        }
    }

    public Iterable<CollegeStudent> getGradeBook() {
        Iterable<CollegeStudent> itr = studentDao.findAll();
        return itr;
    }


    public boolean createGrade(double grade, int studentId, String gradeType) {
        if (!checkIfStudentIsNull(studentId)) {
            return false;
        }
        if (grade > 0 && grade <= 100) {
            if (gradeType.equals("math")) {
                mathGrade.setId(0);
                mathGrade.setGrade(grade);
                mathGrade.setStudentId(studentId);
                mathGradeDao.save(mathGrade);
                return true;
            }
            if (gradeType.equals("science")) {
                scienceGrade.setId(0);
                scienceGrade.setGrade(grade);
                scienceGrade.setStudentId(studentId);
                scienceGradeDao.save(scienceGrade);
                return true;
            }
            if (gradeType.equals("history")) {
                historyGrade.setId(0);
                historyGrade.setGrade(grade);
                historyGrade.setStudentId(studentId);
                historyGradeDao.save(historyGrade);
                return true;
            }
        }
        return false;
    }

    public int deleteGrade(int id, String gradeType) {
        int studentId = 0;
        if (gradeType == "math") {
            Optional<MathGrade> collegeStudent = mathGradeDao.findById(id);
            if (!collegeStudent.isPresent()) {
                return studentId;
            }
            studentId = collegeStudent.get().getId();
            mathGradeDao.deleteById(id);
        }
        if (gradeType == "science") {
            Optional<ScienceGrade> collegeStudent = scienceGradeDao.findById(id);
            if (!collegeStudent.isPresent()) {
                return studentId;
            }
            studentId = collegeStudent.get().getId();
            scienceGradeDao.deleteById(id);
        }
        if (gradeType == "history") {
            Optional<HistoryGrade> collegeStudent = historyGradeDao.findById(id);
            if (!collegeStudent.isPresent()) {
                return studentId;
            }
            studentId = collegeStudent.get().getId();
            historyGradeDao.deleteById(id);
        }
        return studentId;

    }

    public GradebookCollegeStudent getStudentInformation(int id) {
        if(!checkIfStudentIsNull(id)){//If student id does not exist then return null
            return null;
        }
        Optional<CollegeStudent> collegeStudent = studentDao.findById(id);
        Iterable<MathGrade> mathGrades = mathGradeDao.findGradeByStudentId(id);
        Iterable<ScienceGrade> scienceGrades = scienceGradeDao.findGradeByStudentId(id);
        Iterable<HistoryGrade> historyGrades = historyGradeDao.findGradeByStudentId(id);

        ArrayList<Grade>mathGradeList = new ArrayList<>();
        mathGrades.forEach(mathGradeList :: add);
        ArrayList<Grade>scienceGradeList = new ArrayList<>();
        scienceGrades.forEach(scienceGradeList :: add);
        ArrayList<Grade>historyGradeList = new ArrayList<>();
        historyGrades.forEach(historyGradeList :: add);

        StudentGrades studentGrades = new StudentGrades();
        studentGrades.setMathGradeResults(mathGradeList);
        studentGrades.setScienceGradeResults(scienceGradeList);
        studentGrades.setHistoryGradeResults(historyGradeList);

        GradebookCollegeStudent gradebookCollegeStudent = new GradebookCollegeStudent(collegeStudent.get().getId(),collegeStudent.get().getFirstname(),collegeStudent.get().getLastname(),collegeStudent.get().getEmailAddress(),studentGrades);
        return  gradebookCollegeStudent;
    }
}
