package terria1020.calender;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.*;

public class Calender {
    private static final int[] MAX_DAYS = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    private static final String[] DATE = {
            "SN", "MO", "TW", "WE", "TH", "FR", "SA"
    };

    private enum firstDayDate {
        MON, TUE, WED, THU, FRI, SAT, SUN
    }

    private static final int STD_YEAR = 2000;
    private static final int STD_YEAR_MONTH = 1;
    private static final firstDayDate STD_DATE = firstDayDate.SUN; // 2000.1.1 == Sunday

    private static final int LEAP_DAY = 29;

    private HashMap<String, String> schedule;

    public Calender() {
        schedule = new HashMap<>();
    }

    public boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    public int getLastDaysOfMonth(int year, int month) {
        if (isLeapYear(year) && month == 2) return LEAP_DAY;
        return MAX_DAYS[month - 1];
    }

    public int getPushCnt(int year, int month) {
        int cnt = STD_DATE.ordinal();
        int yearAdder = 0;
        int monthadder = 0;

        //년도에 대한 보정 계산
        for (int i = STD_YEAR + 1; i <= year; i++) {
            if (isLeapYear(i - 1)) yearAdder += 2;
            else yearAdder += 1;
        }
        cnt = (cnt + yearAdder) % 7;

        if (month == STD_YEAR_MONTH) return cnt;

        //달에 대한 보정 계산
        for (int i = 2; i <= month; i++) monthadder += getLastDaysOfMonth(year, i - 1) % 7;
        cnt = (cnt + monthadder) % 7;
        return cnt;
    }

    public void printCalender(int year, int month) {
        boolean[] haveSchedules = new boolean[7];

        System.out.printf("\t<%4d년%3d월>", year, month);
        System.out.println();
        for (String c : DATE) System.out.printf("%3s", c);
        System.out.println();
        System.out.println("---------------------");

        int lastDay = getLastDaysOfMonth(year, month);

        int pushcnt = getPushCnt(year, month);
        int escapecnt = pushcnt;

        for (int i = 0; i < pushcnt; i++) {
            System.out.printf("%3c", ' ');
        }

        for (int i = 1; i <= lastDay; i++) {
            String date = year + "-" + month + "-" + i;
            System.out.printf("%3d", i);
            haveSchedules[escapecnt] = hasSchedule(date);
            escapecnt = (escapecnt + 1) % 7;
            if (escapecnt == 0) {
                System.out.println();
                printCalSchedule(pushcnt, haveSchedules);
                Arrays.fill(haveSchedules, false);
                pushcnt = 0;
            }
        }
        System.out.println();
    }

    private void printCalSchedule(int pushcnt, boolean[] haveSchedules) {
        for (int i = 0; i < 7; i++) {
            if (haveSchedules[i]) System.out.printf("%3c", '\'');
            else System.out.printf("%3c", ' ');
        }
        System.out.println();
    }

    public boolean addSchedule(String date, String message) {
        if (schedule.containsKey(date)) {
            if (schedule.get(date).equals(message)) return false;
            String value = schedule.get(date);
            value += "\n" + message;

            schedule.put(date, value);
            return true;
        }
        schedule.put(date, message);
        return true;
    }

    public Optional<String> getSchedule(String date) {
        return Optional.ofNullable(schedule.get(date));
    }

    public boolean hasSchedule(String date) {
        boolean result = schedule.containsKey(date);
        return result;
    }
}
