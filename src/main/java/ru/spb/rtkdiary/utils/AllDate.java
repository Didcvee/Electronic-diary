package ru.spb.rtkdiary.utils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AllDate {
    public static List<String> getDatesByMonthYear(int year, int month) {
        int numDays = LocalDate.of(year, month, 1).lengthOfMonth();
        List<String> dates = new ArrayList<>();

        for (int day = 1; day <= numDays; day++) {
            String date = String.format("%d-%02d-%02d", year, month, day);
            dates.add(date);
        }

        return dates;
    }
}
