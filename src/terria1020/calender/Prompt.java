package terria1020.calender;

import java.util.Scanner;

public class Prompt {

    int month;
    int year;

    private Scanner scanner;
    private Calender calender;

    private interface env {
        String PS1 = "cal > ";
        String PS2 = "year > ";
        String PS3 = "month > ";
    }

    public Prompt() {
        this.scanner = new Scanner(System.in);
        this.calender = new Calender();
    }

    public void run() {
        while (true) {
            System.out.println("년도를 입력하세요.");
            System.out.print(env.PS2);
            year = input();
            System.out.println("달을 입력하세요");
            System.out.print(env.PS3);
            month = input();

            if (month == -1) break;
            else if (month < 1 || month > 12) continue;

            calender.printCalender(year, month);
        }
        printMotd();
    }

    @Deprecated
    public void inputMonth() {
        month = scanner.nextInt();
        scanner.nextLine();
    }

    public int input() {
        int inputNumber = scanner.nextInt();
        scanner.nextLine();
        return  inputNumber;
    }

    public void printMotd() {
        System.out.println("Bye-!");
    }
}
