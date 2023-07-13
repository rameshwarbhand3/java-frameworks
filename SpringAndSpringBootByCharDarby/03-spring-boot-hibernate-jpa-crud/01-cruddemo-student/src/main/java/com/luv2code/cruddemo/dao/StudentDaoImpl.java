package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository //support for component scanning and jdbc exception
public class StudentDaoImpl implements StudentDao {

    //define field entity manager
    private final EntityManager entityManager;

    //inject entity manager dependency using constructor injection
    @Autowired
    public StudentDaoImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    //implement save method
    @Transactional
    @Override
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }
}
