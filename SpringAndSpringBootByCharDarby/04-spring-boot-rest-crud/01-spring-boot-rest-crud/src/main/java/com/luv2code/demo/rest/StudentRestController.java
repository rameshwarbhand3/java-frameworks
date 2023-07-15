package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {
    private List<Student> theStudents;

    //define @postConstruct to load student data...only once
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();
        theStudents.add(new Student("Ram", "Bhand"));
        theStudents.add(new Student("Sham", "Bhand"));
        theStudents.add(new Student("Dinesh", "Pathak"));
    }

    //define endpoint to return list of students
    @GetMapping("/students") //Access this endpoint with /api/students
    public List<Student> getStudents() {

        return theStudents;
    }

    //define endpoint to return /students/{studentId} - return student at index;
    @GetMapping("/students/{studentId}")
    public Student getTheStudent(@PathVariable int studentId) {
        //public Student getTheStudent(@PathVariable("studentId") Integer id) {

        //check index against list size
        if (studentId >= theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("student id not found... " + studentId);
        }

        return theStudents.get(studentId); //return theStudents.get(id);
    }


}
