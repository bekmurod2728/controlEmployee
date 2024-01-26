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
@Table(name = "user_")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String password;
    String userName;

    @OneToOne
    Employee employee;

    public User(String password, String userName) {
        this.password = password;
        this.userName = userName;
    }
}
