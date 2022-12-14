package terria1020.calender;

import java.util.Scanner;

public class Calender {
    public static void main(String[] args) {
        int month;
        int last;
        Scanner sc = new Scanner(System.in);

        System.out.println("달을 입력하세요: ");

        month = sc.nextInt();

        if (month == 2) last = 28;
        else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) last = 31;
        else last = 30;

        System.out.println(month + "월은 " + last + "일까지 있습니다.");
     }
}
