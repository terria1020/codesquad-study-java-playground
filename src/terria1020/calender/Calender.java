package terria1020.calender;

import java.util.Scanner;

public class Calender {
    public static void main(String[] args) {
        int number1;
        int number2;
        Scanner sc;

        sc = new Scanner(System.in);

        System.out.println("두 수를 입력하세요:");
        number1 = sc.nextInt();
        number2 = sc.nextInt();

        System.out.println("두 수의 합은 " + (number1 + number2) + "입니다.");
    }
}
