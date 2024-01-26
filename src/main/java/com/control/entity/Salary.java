package com.control.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Salary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Double primarySalary;
    Double changeSalary;
    Double bonus;

    @ManyToOne
    Employee employee;

    public Salary(Double primarySalary) {
        this.primarySalary = primarySalary;
    }
}
