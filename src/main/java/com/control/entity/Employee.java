package com.control.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String firstName;
    String lastName;
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC")
    Date birthday;
    Boolean gender;
    Integer age;

    @OneToMany(mappedBy = "employee")
    List<KeldiKetdi> keldiKetdilar;
    @ManyToOne
    Rank rank;
    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    List<Salary> salaries;
    @Enumerated(value = EnumType.STRING)
    Role role;
    @OneToOne
    User user;

    public void addSalaries(Salary salary){
        if (salaries == null){
            salaries = new LinkedList<>();
        }
        salaries.add(salary);
    }
}
