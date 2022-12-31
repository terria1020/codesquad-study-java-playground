package terria1020.calender;

import terria1020.calender.dbconnecttion.DatabaseConnector;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Calender {
    public static final String FORMAT_PATTERN = "yyyy-M-d";

    private static final int[] MAX_DAYS = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    private static final String[] DATE = {
            "SN", "MO", "TW", "WE", "TH", "FR", "SA"
    };

    private interface STDDAY {
        enum DayDate {
            MON, TUE, WED, THU, FRI, SAT, SUN
        }

        int YEAR = 2000;
        int MONTH = 1;
        int DATE = DayDate.SUN.ordinal();

        //2000.1.1 == Sunday
    }

    private static final int LEAP_DAY = 29;

    private ArrayList<Schedule> schedules;
    private DatabaseConnector dbConnector;

    public Calender() {
        dbConnector = new DatabaseConnector();
        schedules = getAllSchedules();
    }

    public boolean isLeapYear(int year) {
        return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0);
    }

    public int getLastDaysOfMonth(int year, int month) {
        if (isLeapYear(year) && month == 2) return LEAP_DAY;
        return MAX_DAYS[month - 1];
    }

    public int getPushCnt(int year, int month) {
        int cnt = STDDAY.DATE;
        int yearAdder = 0;
        int monthadder = 0;

        //년도에 대한 보정 계산
        for (int i = STDDAY.YEAR + 1; i <= year; i++) {
            if (isLeapYear(i - 1)) yearAdder += 2;
            else yearAdder += 1;
        }
        cnt = (cnt + yearAdder) % 7;

        if (month == STDDAY.MONTH) return cnt;

        //달에 대한 보정 계산
        for (int i = 2; i <= month; i++) monthadder += getLastDaysOfMonth(year, i - 1) % 7;
        cnt = (cnt + monthadder) % 7;
        return cnt;
    }

    public void printCalender(int year, int month) {
        syncSchedules();
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
            LocalDate localDate = LocalDate.parse(
                    String.join("-", String.valueOf(year), String.valueOf(month), String.valueOf(i)),
                    DateTimeFormatter.ofPattern("yyyy-M-d")
            );
            System.out.printf("%3d", i);
            haveSchedules[escapecnt] = getSchedule(localDate).isPresent();
            escapecnt = (escapecnt + 1) % 7;
            if (escapecnt == 0) {
                System.out.println();
                printCalSchedule(haveSchedules);
                Arrays.fill(haveSchedules, false);
            }
        }
        System.out.println();
    }

    private void printCalSchedule(boolean[] haveSchedules) {
        for (int i = 0; i < 7; i++) {
            if (haveSchedules[i]) System.out.printf("%3c", '\'');
            else System.out.printf("%3c", ' ');
        }
        System.out.println();
    }

    public boolean addSchedule(LocalDate date, String message) {
        syncSchedules();
        Optional<Schedule> found = getSchedule(date);
        if (found.isPresent()) {
            message = found.get().editMessage(message).getMessage();
            try {
                PreparedStatement statement = dbConnector.getConn().prepareStatement("select id from schedule where schedule_date=?");
                statement.setString(1, date.toString());
                int id = statement.executeQuery().getInt("id");
                statement.close();

                String sql = "update schedule set message=? where id=?";
                statement = dbConnector.getConn().prepareStatement(sql);
                statement.setString(1, message);
                statement.setInt(2, id);

                return statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                String sql = "insert into schedule values(null, ?, ?)";
                PreparedStatement statement = dbConnector.getConn().prepareStatement(sql);

                statement.setString(1, date.toString());
                statement.setString(2, message);

                return statement.execute();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public Optional<Schedule> getSchedule(LocalDate date) {
        Optional<Schedule> found = schedules.stream().filter(schedule -> {
                    return schedule.getDate().equals(date);
                })
                .findFirst();
        return found;
    }

    public void syncSchedules() {
        this.schedules = getAllSchedules();
    }

    private ArrayList<Schedule> getAllSchedules() {
        dbConnector.connect();
        Connection conn = dbConnector.getConn();
        ArrayList<ScheduleDto> dtos = new ArrayList<>();
        ArrayList<Schedule> allSchedules = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from schedule");

            while (resultSet.next()) {
                dtos.add(new ScheduleDto(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        dtos.stream().forEach(
                scheduleDto -> allSchedules.add(scheduleDto.toSchedule())
        );

        return allSchedules;
    }

}
