package com.employeeScore.pointing.pointingEmployee.model;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private Long id;
    private String firstName;
    private String lastName;
    private String employeeNumber;
    private LocalDate dateOfBirth;
    private LocalDate hireDate;
    private LocalDate contractEndDate;
    private Double salary;
}


