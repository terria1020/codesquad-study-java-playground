package terria1020.calender;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Calender {
    private static final int[] MAX_DAYS = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    private static final int LEAP_DAY = 29;

    public boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    public int getLastDaysOfMonth(int year, int month) {
        if (isLeapYear(year) && month == 2) return LEAP_DAY;
        return MAX_DAYS[month - 1];
    }

    public void printCalender(int year, int month) {
        System.out.printf("\t<%4d년%3d월>", year, month);
        System.out.println();
        System.out.println(" 일  월  화  수  목  금  토");
        System.out.println("---------------------");

        int lastDay = getLastDaysOfMonth(year, month);

        for (int i = 1; i <= lastDay; i++) {
            System.out.printf("%3d", i);
            if (i % 7 == 0) System.out.println();
        }
        System.out.println();
    }
}
