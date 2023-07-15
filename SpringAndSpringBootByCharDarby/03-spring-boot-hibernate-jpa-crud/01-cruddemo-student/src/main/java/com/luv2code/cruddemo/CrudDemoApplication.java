package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDao;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CrudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDao studentDao) {
        return runner -> {
            //createStudent(studentDao);
             createMultipleStudent(studentDao);
            //readStudent(studentDao);
            //queryForStudents(studentDao);
            //queryForStudentsByLastName(studentDao);
            //updateStudent(studentDao);
            //deleteStudent(studentDao);
            //deleteAllStudents(studentDao);
        };
    }

    private void deleteAllStudents(StudentDao studentDao) {
        System.out.println("Deleting all students.....");
        int numRowsDeleted = studentDao.deleteAll();
        System.out.println("Number of deleted student : "+numRowsDeleted);

    }

    private void deleteStudent(StudentDao studentDao) {
        int id = 2;
        System.out.println("Deleted student id  : "+id);
        studentDao.delete(id);
    }

    private void updateStudent(StudentDao studentDao) {
        //retrieve student based on id
        int id = 2;
        System.out.println("Getting student with id "+id);
        Student student = studentDao.findById(id);

        //update the student
        System.out.println("Updating student ...");
        student.setLastName("Hase");
        studentDao.update(student);

        //display student
        System.out.println("Display updated student....");
        System.out.println(student);
    }

    private void queryForStudentsByLastName(StudentDao studentDao) {
        //get list of student by lastname
        final List<Student> byLastName = studentDao.findByLastName("Bhand");
        //display list
        for (Student student : byLastName) {
            System.out.println(student);
        }
    }

    private void queryForStudents(StudentDao studentDao) {
        //get a list of student
        final List<Student> studentList = studentDao.findAll();
        //display list
        for (Student student : studentList) {
            System.out.println(student);
        }
    }

    private void readStudent(StudentDao studentDao) {
        //create new student
        System.out.println("Create new Student : ");
        Student student = new Student("Akshay", "Warade", "akshay@luv2code.com");

        //save student object
        System.out.println("saved Student");
        studentDao.save(student);

        //display id of saved student
        int theId = student.getId();
        System.out.println("The saved student id is : " + theId);

        //retrieve student basis of id
        System.out.println("Retrieving student with id ");
        Student myStudent = studentDao.findById(theId);

        //display student
        System.out.println("Found the Student : " + myStudent);
    }

    private void createMultipleStudent(StudentDao studentDao) {
        //create the student object
        System.out.println("New student object created : ");
        Student student1 = new Student("Ram", "Bhand", "ram@luv2code.com");
        Student student2 = new Student("Sham", "Bhand", "sham@luv2code.com");
        Student student3 = new Student("Onkar", "Aher", "onkar@luv2code.com");

        //save the student object
        System.out.println("Save the Student...");
        studentDao.save(student1);
        studentDao.save(student2);
        studentDao.save(student3);


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
