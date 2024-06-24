package com.employeeScore.pointing.pointingEmployee.service;

import com.employeeScore.pointing.pointingEmployee.model.PaymentDetails;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EmployeeService {
    private static final double NORMAL_RATE = 100000;
    private static final double NIGHT_RATE = NORMAL_RATE * 1.3;
    private static final double SUNDAY_RATE = NORMAL_RATE * 1.4;
    private static final double HOLIDAY_RATE = NORMAL_RATE * 1.5;
    public int calculateEmployeeWorkHours(int dailyHours, int daysInMonth, int workDaysPerWeek) {
        int workDays = daysInMonth / 7 * workDaysPerWeek;
        return dailyHours * workDays;
    }
    public PaymentDetails calculateEmployeePayment(int dailyHours, int daysInMonth, int workDaysPerWeek,
                                                   int overtimeThreshold, int nightHours, int sundayHours, int holidayHours) {
        int workDays = daysInMonth / 7 * workDaysPerWeek;
        int totalWorkHours = dailyHours * workDays;
        int overtimeHours = Math.max(totalWorkHours - dailyHours * 7 * overtimeThreshold, 0);

        double normalPay = totalWorkHours <= dailyHours * 7 * overtimeThreshold ? totalWorkHours * NORMAL_RATE
                : dailyHours * 7 * overtimeThreshold * NORMAL_RATE;
        double overtimePay30 = overtimeHours > 0 ? overtimeHours * NIGHT_RATE : 0;

        double nightPay = nightHours * NIGHT_RATE;
        double sundayPay = sundayHours * SUNDAY_RATE;
        double holidayPay = holidayHours * HOLIDAY_RATE;

        double totalPay = normalPay + overtimePay30 + nightPay + sundayPay + holidayPay;

        return new PaymentDetails(totalWorkHours, overtimeHours, totalPay);
    }
    public PaymentDetails calculateWorkAndPaymentForScenarioC(int dailyHours, int daysInMonth, int workDaysPerWeek,
                                                              int overtimeThreshold, int nightHours, int sundayHours, int holidayHours) {
        // Calcul des heures de travail pour Rakoto et Rabe selon le scénario C
        int workDays = daysInMonth / 7 * workDaysPerWeek;
        int totalWorkHours = dailyHours * workDays;
        int overtimeHours = Math.max(totalWorkHours - dailyHours * 7 * overtimeThreshold, 16);


        // Calcul du paiement pour les heures normales
        double normalPay = calculateNormalPay(totalWorkHours, overtimeThreshold);

        // Calcul du paiement pour les heures supplémentaires de nuit
        double overtimePay30 = overtimeHours > 0 ? overtimeHours * NIGHT_RATE : 0;

        // Calcul du paiement pour les heures de nuit, dimanche et jours fériés
        double nightPay = nightHours * NIGHT_RATE;
        double sundayPay = sundayHours * SUNDAY_RATE;
        double holidayPay = holidayHours * HOLIDAY_RATE;

        // Calcul du paiement total
        double totalPay = normalPay + overtimePay30 + nightPay + sundayPay + holidayPay;

        return new PaymentDetails(totalWorkHours, overtimeHours, totalPay);
    }

    // Méthode pour calculer le paiement pour les heures normales
    private double calculateNormalPay(int totalWorkHours, int overtimeThreshold) {
        int normalHours = Math.min(totalWorkHours, dailyHours() * 7 * overtimeThreshold);
        return normalHours * NORMAL_RATE;
    }

    // Méthode pour obtenir les heures quotidiennes de travail
    private int dailyHours() {
        return 8;
    }
}
