package com.employeeScore.pointing.pointingEmployee.service;

import com.employeeScore.pointing.pointingEmployee.model.CalendarEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CalendarGeneratorService {

    @Autowired
    private HolidayService holidayService;

    // Méthode pour générer le calendrier de travail pour juin 2024
    public List<CalendarEntry> generateCalendar() {
        List<CalendarEntry> calendar = new ArrayList<>();

        int month = 6;
        int year = 2024;

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        // Génération du calendrier
        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            DayOfWeek dayOfWeek = date.getDayOfWeek();
            boolean isHoliday = holidayService.isHoliday(date);

            String employeeType = determineEmployeeType(dayOfWeek);

            CalendarEntry entry = new CalendarEntry(date, dayOfWeek, employeeType, isHoliday);
            calendar.add(entry);

            date = date.plusDays(1);
        }

        return calendar;
    }

    // Méthode pour déterminer le type d'employé en fonction du jour de la semaine
    private String determineEmployeeType(DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            return "Gardien";
        } else {
            return "Normal";
        }
    }
}
