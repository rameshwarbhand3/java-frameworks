package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Instructor;
import com.luv2code.crudDemo.entity.InstructorDetail;
import com.luv2code.crudDemo.entity.Course;

import java.util.List;

public interface AppDao {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailByID(int theId);

    void deleteInstructorDetailById(int theId);
    List<Course> findCoursesByInstructorId(int theId);
}
