package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Instructor;

public interface AppDao {
    void save(Instructor theInstructor);
    Instructor findInstructorById(int theId);
    void deleteInstructorById(int theId);
}
