package com.luv2code.crudDemo.rest;

import com.luv2code.crudDemo.dao.EmployeeDao;
import com.luv2code.crudDemo.entity.Employee;
import com.luv2code.crudDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //add mapping for GET request "/employees" --------- return list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    //add mapping for GET request /employees/{employeeId} ----- return single employee
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId){
        Employee theEmployee = employeeService.findById(employeeId);
        if(theEmployee == null){
            throw new RuntimeException("Employee not found "+employeeId);
        }
        return theEmployee;
    }
    //add mapping for post request /employees ------ add a new employee
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){
        //also in case they pass as an id in json ----set id to 0;
        //This is force to save new items...instead of update the item.
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //add mapping for put request /employees ------ update the existing employee
    @PutMapping("/employees")
    public  Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    //add mapping for delete request /employees/{employeesId ---- delete the employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee = employeeService.findById(employeeId);
        if(tempEmployee == null){
            throw new RuntimeException("Employee not found "+employeeId);
        }
        employeeService.deleteById(employeeId);
        return "Deleted employee Id is : "+employeeId;
    }
}
