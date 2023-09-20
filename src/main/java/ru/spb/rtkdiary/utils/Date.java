package ru.spb.rtkdiary.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Date {
    public static List<String> getDates(int month, int year, List<Integer> weekdays) {
        List<String> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate startDate = LocalDate.of(year, month, 1);
        LocalDate endDate = startDate.plusMonths(1).minusDays(1);

        LocalDate date = startDate;
        while (!date.isAfter(endDate)) {
            if (isDayOfWeek(date, weekdays)) { // Проверяем, является ли текущий день недели одним из указанных
                dates.add(date.format(formatter));
            }
            date = date.plusDays(1);
        }

        return dates;
    }

    private static boolean isDayOfWeek(LocalDate date, List<Integer> weekdays) {
        int dayOfWeek = date.getDayOfWeek().getValue();
        return weekdays.contains(dayOfWeek);
    }
}
