package com.employeeScore.pointing;

import com.employeeScore.pointing.pointingEmployee.model.PaymentDetails;
import com.employeeScore.pointing.pointingEmployee.service.EmployeeService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeServiceTest {
    private static final int SCALE = 2;

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

        // Arrondir la valeur attendue et la valeur réelle avant de comparer
        BigDecimal expectedTotalPay = new BigDecimal("16650000.00").setScale(SCALE, BigDecimal.ROUND_HALF_UP);
        BigDecimal actualTotalPay = new BigDecimal(paymentDetails.getTotalPay()).setScale(SCALE, BigDecimal.ROUND_HALF_UP);

        assertEquals(expectedTotalPay, actualTotalPay, "Total pay calculated incorrectly");
    }

    @Test
    public void testCalculateWorkAndPaymentForScenarioC() {
        EmployeeService employeeService = new EmployeeService();

        int dailyHours = 8;
        int daysInMonth = 30;
        int workDaysPerWeek = 5;
        int overtimeThreshold = 20;
        int nightHours = 16;
        int sundayHours = 0;
        int holidayHours = 0;

        // Appel de la méthode pour calculer les heures de travail et le paiement pour le scénario C
        PaymentDetails paymentDetails = employeeService.calculateWorkAndPaymentForScenarioC(dailyHours, daysInMonth, workDaysPerWeek,
                overtimeThreshold, nightHours, sundayHours, holidayHours);

        // Assertions pour vérifier les résultats
        assertEquals(160, paymentDetails.getTotalWorkHours(), "Total work hours are incorrect");
        assertEquals(16, paymentDetails.getOvertimeHours(), "Overtime hours are incorrect");
        assertEquals(2080000.0, paymentDetails.getTotalPay(), "Total pay is incorrect");
    }
}
