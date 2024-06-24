package com.employeeScore.pointing;

import com.employeeScore.pointing.pointingEmployee.model.CalendarEntry;
import com.employeeScore.pointing.pointingEmployee.service.CalendarGeneratorService;
import com.employeeScore.pointing.pointingEmployee.service.HolidayService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CalendarGeneratorServiceTest {

    private CalendarGeneratorService calendarGeneratorService;
    private HolidayService holidayService;

    @BeforeEach
    public void setUp() {
        holidayService = mock(HolidayService.class);
        calendarGeneratorService = new CalendarGeneratorService(holidayService);
    }

    @Test
    public void testGenerateCalendar() {
        // Simuler le comportement de HolidayService pour retourner true pour les dates spécifiques
        LocalDate holidayDate1 = LocalDate.of(2024, 6, 17);
        LocalDate holidayDate2 = LocalDate.of(2024, 6, 25);
        LocalDate holidayDate3 = LocalDate.of(2024, 6, 26);

        when(holidayService.isHoliday(holidayDate1)).thenReturn(true);
        when(holidayService.isHoliday(holidayDate2)).thenReturn(true);
        when(holidayService.isHoliday(holidayDate3)).thenReturn(true);

        List<CalendarEntry> calendar = calendarGeneratorService.generateCalendar();

        // Vérification que le calendrier n'est pas null et qu'il contient des entrées
        assertNotNull(calendar);
        assertEquals(30, calendar.size());

        // Vérification de quelques entrées spécifiques dans le calendrier
        LocalDate date1 = LocalDate.of(2024, 6, 1);
        LocalDate date2 = LocalDate.of(2024, 6, 30);

        assertContainsDate(calendar, date1);
        assertContainsDate(calendar, date2);

        // Vérifiez également les jours fériés
        assertContainsHoliday(calendar, holidayDate1);
        assertContainsHoliday(calendar, holidayDate2);
        assertContainsHoliday(calendar, holidayDate3);
    }

    // Méthode utilitaire pour vérifier la présence d'une date dans le calendrier
    private void assertContainsDate(List<CalendarEntry> calendar, LocalDate date) {
        boolean found = calendar.stream()
                .anyMatch(entry -> entry.getDate().equals(date));
        assertEquals(true, found, "Le calendrier ne contient pas la date: " + date);
    }

    // Méthode utilitaire pour vérifier la présence d'un jour férié dans le calendrier
    private void assertContainsHoliday(List<CalendarEntry> calendar, LocalDate date) {
        boolean found = calendar.stream()
                .filter(CalendarEntry::isHoliday)
                .anyMatch(entry -> entry.getDate().equals(date));
        assertEquals(true, found, "Le calendrier ne contient pas le jour férié: " + date);
    }
}
