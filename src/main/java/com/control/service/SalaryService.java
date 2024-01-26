package com.control.service;

import com.control.dto.SalaryDto;
import com.control.entity.Employee;
import com.control.entity.Salary;
import com.control.repository.EmployeeRepository;
import com.control.repository.SalaryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalaryService {
    private final EmployeeRepository employeeRepository;
    private final SalaryRepository salaryRepository;

    public SalaryService(EmployeeRepository employeeRepository, SalaryRepository salaryRepository) {
        this.employeeRepository = employeeRepository;
        this.salaryRepository = salaryRepository;
    }
    public String addSalary(SalaryDto salaryDto){
        Employee employee = employeeRepository.findById(salaryDto.getEmployee_id()).orElse(null);
        if (employee != null){
            Salary salary=new Salary(salaryDto.getPrimarySalary());
            salary.setEmployee(employee);
            employee.addSalaries(salary);
            salaryRepository.save(salary);
            employeeRepository.save(employee);
            return "saved";
        }
        return "dont saved";
    }

    public String deleteSalary(Long id){
        Salary salary = salaryRepository.findById(id).orElse(null);
        if (salary != null){
            Employee employee = employeeRepository.findById(salary.getEmployee().getId()).orElse(null);
            if (employee != null){
                salaryRepository.deleteById(id);
                return "deleted";
            }
            return "dont deleted because salary bind to employee";
        }
        return "dont deleted";
    }
    public List<Salary> getall(){
        return salaryRepository.findAll();
    }

//    public String updateSalary(Salary salary, Double changeSalary, Double bonus){
//
//    }
}
