package com.employeeScore.pointing.pointingEmployee.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.DayOfWeek;
import java.time.LocalDate;

@Data
@AllArgsConstructor
public class CalendarEntry {

    private LocalDate date;
    private DayOfWeek dayOfWeek;
    private String employeeType;
    private boolean isHoliday;

}
