package com.employeeScore.pointing.pointingEmployee.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    private String name;
    private int normalWeeklyHours;
    private double weeklySalary;
    private double allowanceAmount;
}
