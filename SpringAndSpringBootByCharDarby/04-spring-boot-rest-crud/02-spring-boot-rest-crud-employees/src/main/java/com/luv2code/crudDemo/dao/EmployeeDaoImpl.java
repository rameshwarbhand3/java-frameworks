package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmployeeDaoImpl implements EmployeeDao{
        //define field  EntityManager
    private EntityManager entityManager;

    //define constructor to inject entityManager dependency
    @Autowired
    public EmployeeDaoImpl(EntityManager theEntityManager){
        entityManager = theEntityManager;
    }

    @Override
    public List<Employee> findAll() {
        //create a query
        TypedQuery<Employee>theQuery = entityManager.createQuery("from Employee",Employee.class);

        //execute query and get result
        List<Employee> employees = theQuery.getResultList();

        //return the results
        return employees;
    }

    @Override
    public Employee findById(int theId) {
        //get Employee
        Employee myEmployee = entityManager.find(Employee.class,theId);
        //return Employee
        return myEmployee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        //save Employee
        Employee dbEmployee = entityManager.merge(theEmployee);
        //return Employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int theId) {
        //find employee by id
        Employee theEmployee = entityManager.find(Employee.class,theId);
        //delete employee
        entityManager.remove(theEmployee);
    }
}
