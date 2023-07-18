package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer>{
        //just pass entity class and primary key
}
