package com.yarkov.energymanagement.util;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class WorkdaysCalculator {
    public static int getWorkdaysInMonth(Integer year, Integer month) {
        LocalDate start = LocalDate.of(year, month, 1);
        LocalDate end = start.withDayOfMonth(start.lengthOfMonth());
        int workdays = 0;
        while (!start.isAfter(end)) {
            if (start.getDayOfWeek() != DayOfWeek.SATURDAY && start.getDayOfWeek() != DayOfWeek.SUNDAY) {
                workdays++;
            }
            start = start.plusDays(1);
        }
        return workdays;
    }
}
