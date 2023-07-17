package com.luv2code.crudDemo.dao;

import com.luv2code.crudDemo.entity.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class EmloyeeDaoImpl implements EmployeeDao{

    @Override
    public List<Employee> findAll() {
        return null;
    }
}
