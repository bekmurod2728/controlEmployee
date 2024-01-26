package com.control.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Rank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String employeeRank;
    @JsonIgnore
    @OneToMany(mappedBy = "rank")
    List<Employee> employees;

    public Rank(String employeeRank) {
        this.employeeRank = employeeRank;
    }

    public void addEmployees(Employee employee){
        if (employees == null){
            employees = new LinkedList<>();
        }
        employees.add(employee);
    }
}
