package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDao;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDao) {
        return runner -> {
            createStudent(studentDao);
        };
    }

    private void createStudent(StudentDao studentDao) {
        //create the student object
        System.out.println("Create new student object : ");
        Student theStudent = new Student("Ram", "Bhand", "ram@luv2code.com");

        //save the student object
        System.out.println("Save the Student...");
        studentDao.save(theStudent);

        //display the id of saved object
        System.out.println("Id of saved student : " + theStudent.getId());
    }

}
