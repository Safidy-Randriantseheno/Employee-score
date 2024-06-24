package com.employeeScore.pointing.pointingEmployee.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTracking {
    private Long id;
    private Employee employee;
    private LocalDate date;
    private Integer hoursWorked;
}
