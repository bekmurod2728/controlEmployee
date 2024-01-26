package com.control.controller;

import com.control.dto.EmployeeDto;
import com.control.entity.Employee;
import com.control.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping("/add")
    public String add(@RequestBody EmployeeDto employeeDto){
        return employeeService.addEmployee(employeeDto);
    }
    @GetMapping("/show")
    public List<Employee> show(){
        return employeeService.getall();
    }

    @DeleteMapping("/delete/{id}")
    public String del(@PathVariable Long id){
        return employeeService.deleteEmployee(id);
    }
}
