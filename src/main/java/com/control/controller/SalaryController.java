package com.control.controller;

import com.control.dto.SalaryDto;
import com.control.entity.Salary;
import com.control.service.SalaryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/salary")
public class SalaryController {
    private final SalaryService service;

    public SalaryController(SalaryService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public String add(@RequestBody SalaryDto dto){

        return service.addSalary(dto);
    }
    @GetMapping("/show")
    public List<Salary> show(){

        return service.getall();
    }

    @DeleteMapping("/delete/{id}")
    public String del(@PathVariable Long id)
    {
        return service.deleteSalary(id);
    }
}
