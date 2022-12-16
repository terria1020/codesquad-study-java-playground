package terria1020.calender;

import java.util.Scanner;

public class Prompt {
    private static final String PS1 = "cal > ";

    private int month;
    private Scanner scanner;
    private Calender calender;

    public Prompt() {
        this.scanner = new Scanner(System.in);
        this.calender = new Calender();
    }

    public void run() {
        while (true) {
            System.out.println("달을 입력하세요");
            System.out.print(PS1);

            inputMonth();

            if (month == -1) break;
            else if (month < 1 || month > 12) continue;

            calender.printCalender();
        }
        printMotd();
    }

    public void inputMonth() {
        month = scanner.nextInt();
        scanner.nextLine();
    }

    public void printMotd() {
        System.out.println("Bye-!");
    }
}
