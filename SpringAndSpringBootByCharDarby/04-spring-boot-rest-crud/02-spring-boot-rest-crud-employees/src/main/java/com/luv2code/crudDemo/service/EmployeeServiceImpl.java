package com.luv2code.crudDemo.service;

import com.luv2code.crudDemo.dao.EmployeeDao;
import com.luv2code.crudDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceDaoImpl implements ServiceDao{
    private EmployeeDao employeeDao;
    @Autowired
    public ServiceDaoImpl(EmployeeDao theEmployeeDao){
        employeeDao = theEmployeeDao;
    }
    @Override
    public List<Employee> findAll() {
        return employeeDao.findAll();
    }
}
