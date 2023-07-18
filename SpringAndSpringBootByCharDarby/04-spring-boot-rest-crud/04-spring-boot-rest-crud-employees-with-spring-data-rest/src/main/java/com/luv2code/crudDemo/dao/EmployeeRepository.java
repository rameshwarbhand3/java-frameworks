package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@RepositoryRestResource(path="members") //to give custom path
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
        //just pass entity class and primary key
}
