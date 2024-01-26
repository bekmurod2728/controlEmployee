package com.control.service;

import com.control.entity.Employee;
import com.control.entity.Rank;
import com.control.entity.Role;
import com.control.entity.User;
import com.control.repository.EmployeeRepository;
import com.control.dto.EmployeeDto;
import com.control.repository.RankRepository;
import com.control.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final RankRepository rankRepository;
    private final UserRepository userRepository;

    public EmployeeService(EmployeeRepository employeeRepository, RankRepository rankRepository, UserRepository userRepository) {
        this.employeeRepository = employeeRepository;
        this.rankRepository = rankRepository;
        this.userRepository = userRepository;
    }

    public String addEmployee(EmployeeDto employeeDto){
        Rank rank = rankRepository.findById(employeeDto.getRank_id()).orElse(null);
        Role role=Role.valueOf(employeeDto.getRole());
        User user = userRepository.findById(employeeDto.getUser_id()).orElse(null);
        if (rank != null && role != null && user !=null){
            Employee employee = mappedToEmployee(employeeDto);
            employee.setUser(user);
            employee.setRank(rank);
            employee.setRole(role);
            rank.addEmployees(employee);
            employeeRepository.save(employee);
            rankRepository.save(rank);
            userRepository.save(user);
            return "saved";
        }
        return "dont saved";
    }
    public String deleteEmployee(Long id){
        Employee employee = employeeRepository.findById(id).orElse(null);
        if (employee != null){
            employeeRepository.deleteById(id);
            return "deleted";
        }
        return "dont deleted";
    }
    public List<Employee> getall(){
        return employeeRepository.findAll();
    }

    private Employee mappedToEmployee(EmployeeDto employeeDto){
        Employee employee=Employee.builder()
                .age(employeeDto.getAge())
                .birthday(employeeDto.getBirthday())
                .firstName(employeeDto.getFirstName())
                .lastName(employeeDto.getLastName())
                .gender(employeeDto.getGender())
                .build();
        return employee;
    }
}
