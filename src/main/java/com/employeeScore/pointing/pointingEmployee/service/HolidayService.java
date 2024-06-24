package com.employeeScore.pointing.pointingEmployee.service;

import org.springframework.stereotype.Service;
import java.time.LocalDate;

@Service
public class HolidayService {

    // Méthode pour vérifier si une date est un jour férié
    public boolean isHoliday(LocalDate date) {
        int day = date.getDayOfMonth();
        int month = date.getMonthValue();
        return (day == 17 && month == 6) || (day == 25 && month == 6) || (day == 26 && month == 6);
    }
}
