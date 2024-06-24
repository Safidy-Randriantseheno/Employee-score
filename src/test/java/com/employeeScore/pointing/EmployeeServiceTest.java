package com.employeeScore.pointing;

import com.employeeScore.pointing.pointingEmployee.model.PaymentDetails;
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
    @Test
    public void testCalculateEmployeePayment() {
        int dailyHours = 8;
        int daysInMonth = 30;
        int workDaysPerWeek = 5;
        int overtimeThreshold = 20;
        int nightHours = 5;
        int sundayHours = 0;
        int holidayHours = 0;

        EmployeeService employeeService = new EmployeeService();
        PaymentDetails paymentDetails = employeeService.calculateEmployeePayment(
                dailyHours, daysInMonth, workDaysPerWeek, overtimeThreshold, nightHours, sundayHours, holidayHours);

        // Assert that the calculated total work hours, overtime hours, and total pay match the expected values
        assertEquals(160, paymentDetails.getTotalWorkHours(), "Total work hours calculated incorrectly");
        assertEquals(0, paymentDetails.getOvertimeHours(), "Overtime hours calculated incorrectly");
        assertEquals(100000 * 160, paymentDetails.getTotalPay(), "Total pay calculated incorrectly");
    }
}
