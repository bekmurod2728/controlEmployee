package com.control.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class KeldiKetdi {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    Long id;
    LocalDateTime arrive;
    LocalDateTime went;
    Boolean isCame;

    @ManyToOne
    Employee employee;
}
