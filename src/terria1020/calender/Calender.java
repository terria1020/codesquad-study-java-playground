package terria1020.calender;

import java.util.Scanner;

public class Calender {

    private static final int[] MAX_DAYS = {
            31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31
    };

    private static final String PROMPT = "> ";

    public int getLastDaysOfMonth(int month) {
        return MAX_DAYS[month - 1];
    }

    public static int getMonth() {
        System.out.println("달을 입력하세요: ");
        System.out.print(PROMPT);

        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }

    public static void main(String[] args) {
        int month;
        int maxDay;
        int count;
        Scanner sc = new Scanner(System.in);
        Calender calender = new Calender();

        while (true) {
            month = getMonth();
            if (month == -1) break;

            maxDay = calender.getLastDaysOfMonth(month);
            System.out.println(month + "월은 " + maxDay + "일까지 있습니다.");
        }

        System.out.println("Have a nice day!");
    }
}
