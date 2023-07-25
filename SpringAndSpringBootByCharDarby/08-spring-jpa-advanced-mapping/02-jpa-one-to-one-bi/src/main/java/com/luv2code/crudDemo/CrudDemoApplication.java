package com.luv2code.crudDemo;

import com.luv2code.crudDemo.dao.AppDao;
import com.luv2code.crudDemo.entity.Instructor;
import com.luv2code.crudDemo.entity.InstructorDetail;
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
    public CommandLineRunner commandLineRunner(AppDao appDao) {
        return runner -> {
            //createInstructor(appDao);
            //findInstructor(appDao);
            //deleteInstructor(appDao);
            //findInstructorDetail(appDao);
            deleteInstructorDetail(appDao);

        };
    }

    private void deleteInstructorDetail(AppDao appDao) {
        int theId = 12;
        System.out.println("Deleted InstructorDetail : "+theId);
        appDao.deleteInstructorDetailById(theId);
        System.out.println("successfully deleted");
    }

    private void findInstructorDetail(AppDao appDao) {
        int theId = 2;
        System.out.println("Instructor Detail  found : ");
        InstructorDetail instructorDetailByID = appDao.findInstructorDetailByID(theId);
        System.out.println("Instructor Detail : "+instructorDetailByID);
        System.out.println("Instructor corresponding to instructor detail : "+instructorDetailByID.getInstructor());
        System.out.println("Done");

    }

    private void deleteInstructor(AppDao appDao) {
        int theId = 9;
        appDao.deleteInstructorById(theId);
        System.out.println("Instructor deleted id : "+theId);
    }


    private void findInstructor(AppDao appDao) {
        Instructor theInstructor = new Instructor();
        int theId = 5;
        Instructor instructorById = appDao.findInstructorById(theId);
        System.out.println("Instructor : "+instructorById);
        System.out.println("Instructor_Detail only "+instructorById.getInstructorDetail());
    }

    private void createInstructor(AppDao appDao) {
		/*//create the instructor
		Instructor theInstructor = new Instructor("Chad","Darby","darby@luv2code.com");

		//create the Instructor Detail
		InstructorDetail theInstructionDetail = new InstructorDetail("http:www.luv2code.com","Luv 2 code!!!");

		 */

        //create the instructor
        Instructor theInstructor = new Instructor("Madhu","Patel","madhu@luv2code.com");

        //create the Instructor Detail
        InstructorDetail theInstructionDetail = new InstructorDetail("http:www.luv2code.com","Guitar");


        //associate the objects
		theInstructor.setInstructorDetail(theInstructionDetail);

		//save the instructor
		System.out.println("Saving instructor "+theInstructor);
		appDao.save(theInstructor);
		System.out.println("Done!");
	}
}
