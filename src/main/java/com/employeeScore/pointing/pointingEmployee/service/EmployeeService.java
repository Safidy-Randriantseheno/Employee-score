package com.employeeScore.pointing.pointingEmployee.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
    public int calculateEmployeeWorkHours(int dailyHours, int daysInMonth, int workDaysPerWeek) {
        int workDays = daysInMonth / 7 * workDaysPerWeek;
        return dailyHours * workDays;
    }
}
