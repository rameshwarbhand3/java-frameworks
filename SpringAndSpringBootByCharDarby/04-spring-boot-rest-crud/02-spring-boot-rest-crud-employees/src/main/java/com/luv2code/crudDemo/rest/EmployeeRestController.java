package com.luv2code.crudDemo.rest;

import com.luv2code.crudDemo.dao.EmployeeDao;
import com.luv2code.crudDemo.entity.Employee;
import com.luv2code.crudDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    //define field for employeeService
    private EmployeeService employeeService;
    //create constructor to inject employeeDao dependency

    public  EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }
    //expose "/employees" and return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }
    //add mapping for GET request /employees/{employeeId} and return single employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee not found "+employeeId);
        }
        return theEmployee;
    }
}
