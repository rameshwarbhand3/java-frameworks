package com.luv2code.crudDemo.service;

import com.luv2code.crudDemo.entity.Employee;

import java.util.List;

public interface ServiceDao {
    List<Employee>findAll();
}
