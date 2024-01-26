package com.control.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PACKAGE)
public class SalaryDto {
    Double primarySalary;
    Long employee_id;
}
