package com.luv2code.crudDemo;

import com.luv2code.crudDemo.dao.AppDao;
import com.luv2code.crudDemo.entity.*;
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
    public CommandLineRunner commandLineRunner(AppDao appDao) {
        return runner -> {
            //createInstructor(appDao);
            //findInstructor(appDao);
            //deleteInstructor(appDao);
            //findInstructorDetail(appDao);
            //deleteInstructorDetail(appDao);
            //createInstructorWithCourses(appDao);
            //findInstructorWithCourses(appDao);
            //findCoursesForInstructor(appDao);
            //findInstructorWithCoursesJoinFetch(appDao);
            //updateInstructor(appDao);
            //findCourse(appDao);
            //updateCourse(appDao);
            //deleteCourse(appDao);

  //          Method for courses and reviews.
            //createCourseAndReviews(appDao);
            //retrieveCourseAndReview(appDao);
            //deleteCourseAndReviews(appDao);

//         Method for courses and student
            //createCoursesAndStudents(appDao);
           // findCourseAndStudents(appDao);
            //findStudentAndCourses(appDao);
            //addMoreCoursesForStudent(appDao);
            //deleteCourse(appDao);
            deleteStudent(appDao);
        };
    }

    private void deleteStudent(AppDao appDao) {
        int theId  = 2;
        System.out.println("Deleting Student id : "+theId);
        appDao.deleteStudentById(theId);
        System.out.println("Done");

    }

    private void addMoreCoursesForStudent(AppDao appDao) {
        int theId = 2;
        Student thestudent = appDao.findStudentAndCoursesByStudentId(theId);

        //create more course for given student
        Course course1 = new Course("Advanced Java");
        Course course2  = new Course("Compete Java");

        //Add the courses to student
        thestudent.addCourses(course1);
        thestudent.addCourses(course2);

        //update into courses corrosponding student
        System.out.println("Saving student : "+thestudent);
        System.out.println("Associated courses : "+thestudent.getCourses());
        appDao.update(thestudent);
        System.out.println("Done");
    }


    private void findStudentAndCourses(AppDao appDao) {
        int theId = 2;
        Student student = appDao.findStudentAndCoursesByStudentId(theId);
        System.out.println("Student with id : "+theId);
        System.out.println("Student : "+student);
        System.out.println("Courses : "+student.getCourses());
        System.out.println("Done");

    }

    private void findCourseAndStudents(AppDao appDao) {
        int theId = 10;
        System.out.println("Course with id : "+theId);
        Course course = appDao.findCourseAndStudentsByCourseId(theId);
        System.out.println("Course : "+course);
        System.out.println("students : "+course.getStudents());
        System.out.println("Done");

    }

    private void createCoursesAndStudents(AppDao appDao) {
        //create a course
        Course course = new Course("Core Java");

        //create a student
        Student students1 = new Student("Ram","Bhand","ram@luv2code.com");
        Student students2 = new Student("Akash","Bhamare","akash@luv2code.com");
        Student students3 = new Student("Onkar","Aher","onkar@luv2code.com");

        //add students to course
        course.addStudents(students1);
        course.addStudents(students2);
        course.addStudents(students3);

        //save the course and associated student
        System.out.println("Saving ths Course : "+course);
        System.out.println("Saving students : "+course.getStudents());
        appDao.save(course);
        System.out.println("Done!!!");
    }

    private void deleteCourseAndReviews(AppDao appDao) {
        int theId  = 10;
        System.out.println("Deleting course id : "+theId);
        appDao.deleteCourseById(theId);
        System.out.println("Done");

    }

    private void retrieveCourseAndReview(AppDao appDao) {
        int theId = 10;
        System.out.println("Course with Id : " + theId);
        Course courseAndReviewByCourseId = appDao.findCourseAndReviewsByCourseId(theId);
        System.out.println("Course : " + courseAndReviewByCourseId);
        System.out.println("Reviews for Course :" + courseAndReviewByCourseId.getReviews());
        System.out.println("Done");
    }

    private void createCourseAndReviews(AppDao appDao) {
        //create a course
        Course tempCourse = new Course("FullStack java by DurgaSoft");
        //Add reviews
        tempCourse.addReviews(new Review("This is a good course for java"));
        tempCourse.addReviews(new Review("Many concepts of web development clear"));
        tempCourse.addReviews(new Review("Taught using real time eample"));
        System.out.println("saving the courses : ");
        System.out.println(tempCourse);
        System.out.println(tempCourse.getReviews());
        appDao.save(tempCourse);
        System.out.println("Done");
    }

    private void deleteCourse(AppDao appDao) {
        int theId = 10;
        System.out.println("Course delete id : " + theId);
        appDao.deleteCourseById(theId);
        System.out.println("Done");
    }

    private void updateCourse(AppDao appDao) {
        int theId = 10;
        System.out.println("Find course by Id : " + theId);
        Course course = appDao.findCourseByID(theId);
        System.out.println("Update course id " + theId);
        course.setTitle("React");
        appDao.update(course);
        System.out.println("Course " + course);

    }

    private void findCourse(AppDao appDao) {
        int theId = 10;
        System.out.println("Course Find id : " + theId);
        Course theCourse = appDao.findCourseByID(theId);
        System.out.println("Course : " + theCourse);

    }

    private void updateInstructor(AppDao appDao) {
        int theId = 1;
        System.out.println("finding Instructor by Id " + theId);
        Instructor instructor = appDao.findInstructorById(theId);
        System.out.println("Updating Instructor id : " + theId);
        instructor.setLastName("Bhand");
        appDao.update(instructor);
        System.out.println("Instructor " + instructor);
        System.out.println("Done!!!");

    }

    private void findInstructorWithCoursesJoinFetch(AppDao appDao) {
        int theId = 1;
        //find Instructor
        System.out.println("Instructor id : " + theId);
        Instructor tempInstructor = appDao.findInstructorByIdJoinFetch(theId);
        System.out.println("Instructor : " + tempInstructor);
        System.out.println("Instructor with Courses " + tempInstructor.getCourses());
    }

    private void findCoursesForInstructor(AppDao appDao) {
        int theId = 1;
        //find instructor
        System.out.println("Instructor with Id : " + theId);
        Instructor tempInstructor = appDao.findInstructorById(theId);
        System.out.println("tempInstructor " + tempInstructor);

        //fetch or retrieve the courses for an instructor
        System.out.println("Courses for Instructor id " + theId);
        List<Course> courses = appDao.findCoursesByInstructorId(theId);

        //associate the objects
        tempInstructor.setCourses(courses);
        System.out.println("courses with Instructor : " + tempInstructor.getCourses());
        System.out.println("Done!!!");

    }

    private void findInstructorWithCourses(AppDao appDao) {
        int theId = 1;
        System.out.println("Instructor with Id : " + theId);
        Instructor tempInstructor = appDao.findInstructorById(theId);
        System.out.println("tempInstructor " + tempInstructor);
        //for fetching courses we have to override default fetch type i.e lazy.we have to make it as Eager.
        System.out.println("Courses with Instructor " + tempInstructor.getCourses());
        System.out.println("Done!!!");
    }

    private void createInstructorWithCourses(AppDao appDao) {
        //create the instructor
        Instructor theInstructor = new Instructor("susan", "Public", "susan@luv2code.com");

        //create the Instructor Detail
        InstructorDetail theInstructionDetail = new InstructorDetail("http:www.youtube.com", "Video games");


        //associate the objects
        theInstructor.setInstructorDetail(theInstructionDetail);

        //add some courses
        Course tempCourse1 = new Course("Core-Java");
        Course tempCourse2 = new Course("Adv-Java");
        Course tempCourse3 = new Course("Java-Framework");

        //Add this to instructor;
        theInstructor.add(tempCourse1);
        theInstructor.add(tempCourse2);
        theInstructor.add(tempCourse3);

        //save instructor to database(this will also save courses)
        //because cascade.persist
        System.out.println("Saving instructor : " + theInstructor);
        System.out.println("The Courses : " + theInstructor.getCourses());
        appDao.save(theInstructor);

    }

    private void deleteInstructorDetail(AppDao appDao) {
        int theId = 12;
        System.out.println("Deleted InstructorDetail : " + theId);
        appDao.deleteInstructorDetailById(theId);
        System.out.println("successfully deleted");
    }

    private void findInstructorDetail(AppDao appDao) {
        int theId = 2;
        System.out.println("Instructor Detail  found : ");
        InstructorDetail instructorDetailByID = appDao.findInstructorDetailByID(theId);
        System.out.println("Instructor Detail : " + instructorDetailByID);
        System.out.println("Instructor corresponding to instructor detail : " + instructorDetailByID.getInstructor());
        System.out.println("Done");

    }

    private void deleteInstructor(AppDao appDao) {
        int theId = 1;
        System.out.println("Deleting instructor id : " + theId);
        appDao.deleteInstructorById(theId);
        System.out.println("Done!!!");

    }


    private void findInstructor(AppDao appDao) {
        Instructor theInstructor = new Instructor();
        int theId = 5;
        Instructor instructorById = appDao.findInstructorById(theId);
        System.out.println("Instructor : " + instructorById);
        System.out.println("Instructor_Detail only " + instructorById.getInstructorDetail());
    }

    private void createInstructor(AppDao appDao) {
		/*//create the instructor
		Instructor theInstructor = new Instructor("Chad","Darby","darby@luv2code.com");

		//create the Instructor Detail
		InstructorDetail theInstructionDetail = new InstructorDetail("http:www.luv2code.com","Luv 2 code!!!");

		 */

        //create the instructor
        Instructor theInstructor = new Instructor("Madhu", "Patel", "madhu@luv2code.com");

        //create the Instructor Detail
        InstructorDetail theInstructionDetail = new InstructorDetail("http:www.luv2code.com", "Guitar");


        //associate the objects
        theInstructor.setInstructorDetail(theInstructionDetail);

        //save the instructor
        System.out.println("Saving instructor " + theInstructor);
        appDao.save(theInstructor);
        System.out.println("Done!");
    }
}
