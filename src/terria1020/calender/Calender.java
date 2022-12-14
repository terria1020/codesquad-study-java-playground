package terria1020.calender;

import java.util.Scanner;

public class Calender {

    private static final int[] MAX_DAYS = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    public int getLastDaysOfMonth(int month) {
        return MAX_DAYS[month - 1];
    }

    public static void main(String[] args) {
        int month;
        Scanner sc = new Scanner(System.in);
        Calender calender = new Calender();

        System.out.println("달을 입력하세요: ");

        month = sc.nextInt();

        System.out.println(month + "월은 " + calender.getLastDaysOfMonth(month) + "일까지 있습니다.");
    }
}
