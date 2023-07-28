package com.luv2code.crudDemo;

import com.luv2code.crudDemo.dao.AppDao;
import com.luv2code.crudDemo.entity.Course;
import com.luv2code.crudDemo.entity.Instructor;
import com.luv2code.crudDemo.entity.InstructorDetail;
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
            deleteCourse(appDao);



        };
    }

    private void deleteCourse(AppDao appDao) {
        int theId = 10;
        System.out.println("Course delete id : "+theId);
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
        System.out.println("Deleting instructor id : "+theId);
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
