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
        int[] months;
        int[] maxDays;
        int count;
        Scanner sc = new Scanner(System.in);
        Calender calender = new Calender();

        System.out.println("반복 횟수를 입력하세요:");
        count = sc.nextInt();
        sc.nextLine();

        months = new int[count];
        maxDays = new int[count];
        for (int i = 0; i < count; i++) {
            System.out.println("달을 입력하세요: ");

            months[i] = sc.nextInt();
            sc.nextLine();

            maxDays[i] = calender.getLastDaysOfMonth(months[i]);
        }

        for (int i = 0; i < count; i++) {
            System.out.println(months[i] + "월은 " + maxDays[i] + "일까지 있습니다.");
        }
    }
}
