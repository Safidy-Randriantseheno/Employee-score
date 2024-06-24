package com.employeeScore.pointing;

import com.employeeScore.pointing.pointingEmployee.service.EmployeeService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {

    @Test
    public void testCalculateRakotoWorkHours() {
        int dailyHours = 8;
        int daysInMonth = 30;
        int workDaysPerWeek = 5;

        EmployeeService employeeService = new EmployeeService();
        int rakotoWorkHours = employeeService.calculateEmployeeWorkHours(dailyHours, daysInMonth, workDaysPerWeek);

        assertEquals(160, rakotoWorkHours, "Rakoto's work hours calculated incorrectly");
    }
}
