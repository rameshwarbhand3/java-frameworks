package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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

    @Override
    //No need to write @Transactional because we do not change the database just read from database.
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll() {
        //create query
        //TypedQuery<Student>theQuery = entityManager.createQuery("FROM Student",Student.class);
        // TypedQuery<Student>theQuery = entityManager.createQuery("FROM Student order by lastName asc",Student.class);
        TypedQuery<Student> theQuery = entityManager.createQuery(" FROM Student order by lastName desc", Student.class);


        //return query results
        return theQuery.getResultList();
    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        //create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student WHERE lastName=:theData", Student.class);
        //set query parameter
        theQuery.setParameter("theData", theLastName);
        //return resultlist
        return theQuery.getResultList();
    }

    @Override
    @Transactional//this method change student  object in database
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer theId) {
        //retrieve the student
        Student theStudent = entityManager.find(Student.class,theId);
        //delete from database
        entityManager.remove(theStudent);
    }

    @Override
    @Transactional//since we are modifying the database
    public int deleteAll() {
        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();

        return numRowsDeleted;
    }
}
