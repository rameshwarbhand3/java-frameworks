package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeSErvice) {
        employeeService = theEmployeeSErvice;
    }

    // add mapping for "/list"
    @GetMapping("/list")
    public String listEmployees(Model theModel) {
        //Get employees from db
        List<Employee> theEmployees = employeeService.findAll();

        // add to the spring model
        theModel.addAttribute("employees", theEmployees);
        return "employees/list-employees";
    }

    //add mapping for "/showFormForAdd
    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model theModel) {
        //create model attribute to bind form data
        Employee theEmployee = new Employee();
        theModel.addAttribute("employees", theEmployee);
        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){
        //save the Employee
        employeeService.save(theEmployee);

        //use a redirect to prevent duplicate submissions
        return "redirect:/employees/list";

    }
    @GetMapping("/showFormForUpdate")
    public String showUpdateForm(@RequestParam("employeeId") int theId, Model theModel){
        //get the employee from service
        Employee theEmployee = employeeService.findById(theId);

        //set employee as model attribute to prepopulate the form
        theModel.addAttribute("employees", theEmployee);

        //send over to our form
        return "employees/employee-form";

    }

    @GetMapping("/delete")
    public String deleteEmlployee(@RequestParam("employeeId")int theId){
        //delete the employee
        employeeService.deleteById(theId);

        //redirect to /employees/list
        return "redirect:/employees/list";
    }

}









