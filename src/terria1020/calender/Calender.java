package terria1020.calender;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Calender {
    private static final int[] MAX_DAYS = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public int getLastDaysOfMonth(int month) {
        return MAX_DAYS[month - 1];
    }


    public void printCalender(int month) {
        int year = LocalDateTime.now().getYear();

        System.out.printf("\t<%4d년%3d월>", year, month);
        System.out.println();
        System.out.println(" 일  월  화  수  목  금  토");
        System.out.println("---------------------");

        int lastDay = getLastDaysOfMonth(month);

        for (int i = 1; i <= lastDay; i++) {
            System.out.printf("%3d", i);
            if (i % 7 == 0) System.out.println();
        }
        System.out.println();
    }

    @Deprecated
    public static int getMonth() {
        System.out.println("달을 입력하세요: ");
        System.out.print("> ");

        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
}
