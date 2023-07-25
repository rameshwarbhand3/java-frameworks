package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Instructor;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AppDaoImpl implements AppDao {

    //define the field entityManager;
    private EntityManager entityManager;

    //Inject dependency by creating constructor
    @Autowired
    public AppDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);//This will also save instructor detail object because of CASCADE.ALL
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class,theId);//This will also retrieve instructor detail because of default fetch eager one to one mapping.

    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the instructor
        Instructor instructor = entityManager.find(Instructor.class, theId);

        //delete the instructor
        entityManager.remove(instructor);
    }
}



