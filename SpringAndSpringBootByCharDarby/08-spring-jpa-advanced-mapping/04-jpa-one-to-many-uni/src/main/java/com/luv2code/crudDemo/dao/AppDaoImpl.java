package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Course;
import com.luv2code.crudDemo.entity.Instructor;
import com.luv2code.crudDemo.entity.InstructorDetail;
import com.luv2code.crudDemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        return entityManager.find(Instructor.class, theId);//This will also retrieve instructor detail because of default fetch eager one to one mapping.

    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        //retrieve the instructor
        Instructor instructor = entityManager.find(Instructor.class, theId);

        List<Course> courses = instructor.getCourses();

        //break association between instructor and courses
        for (Course course : courses) {
            course.setInstructor(null);
        }

        //delete the instructor
        entityManager.remove(instructor);
    }

    @Override
    public InstructorDetail findInstructorDetailByID(int theId) {
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);
        return instructorDetail;
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {
        //retrieve the instruction detail
        InstructorDetail instructorDetail = entityManager.find(InstructorDetail.class, theId);

        //remove the associated object references
        //break bidirectional link
        //by this way we can only delete Instructor Detail
        instructorDetail.getInstructor().setInstructorDetail(null);


        //delete instruction detail
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {
        //create query
        TypedQuery<Course> theQuery = entityManager.createQuery("from Course where instructor.id = :data", Course.class);
        theQuery.setParameter("data", theId);

        //return result;
        List<Course> courses = theQuery.getResultList();
        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {
        //create Query
        TypedQuery<Instructor> theQuery = entityManager.createQuery("select i from Instructor i " +
                "JOIN FETCH i.courses   where i.id = :data", Instructor.class);
        theQuery.setParameter("data", theId);
        Instructor instructor = theQuery.getSingleResult();
        return instructor;
    }

    @Override
    @Transactional
    public void update(Instructor theInstructor) {
        entityManager.merge(theInstructor);
    }

    @Override
    @Transactional
    public void update(Course course) {
        entityManager.merge(course);
    }

    @Override
    public Course findCourseByID(int theId) {
        Course course = entityManager.find(Course.class, theId);
        return course;
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {
        //find course
        Course course = entityManager.find(Course.class, theId);
        //remove the course
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {
//        Course tempCourse = entityManager.find(Course.class, theId);
//        return tempCourse;//If we use fetch type eager its ok but if we use lazy fetch type we use below code

            TypedQuery<Course>theQuery = entityManager.createQuery("select c from Course c " +
                    "JOIN FETCH c.reviews where c.id = :data", Course.class);
            theQuery.setParameter("data",theId);
             return theQuery.getSingleResult();
    }

    @Override
    public Course findCourseAndStudentsByCourseId(int theId) {
       TypedQuery<Course>theQuery = entityManager.createQuery("select c from Course c JOIN FETCH c.students where c.id = :data",Course.class);
       theQuery.setParameter("data",theId);
        Course course = theQuery.getSingleResult();
        return course;
    }

    @Override
    public Student findStudentAndCoursesByStudentId(int theId) {
        TypedQuery<Student>theQuery = entityManager.createQuery("select s from Student s JOIN FETCH s.courses where s.id = :data",Student.class);
        theQuery.setParameter("data",theId);
        Student student = theQuery.getSingleResult();
        return student;
    }

    @Override
    @Transactional
    public void update(Student tempStudent) {
        entityManager.merge(tempStudent);
    }

    @Override
    @Transactional
    public void deleteStudentById(int theId) {
        Student student = entityManager.find(Student.class, theId);
        entityManager.remove(student);
    }

}



