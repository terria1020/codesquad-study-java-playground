package terria1020.calender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;

public class Prompt {

    int month;
    int year;

    private Scanner scanner;
    private Calender calender;

    private interface ENV {
        String CONSOLE = "+================+" +
                "\n| 1. 일정 등록" +
                "\n| 2. 일정 검색" +
                "\n| 3. 달력 보기" +
                "\n| 4. 달력 검색" +
                "\n| h. 도움말" +
                "\n| q. 종료" +
                "\n+================+" +
                "\n명령: (1, 2, 3, 4, h, q)";
        String PS1 = "cal > ";
        String PS2 = "year > ";
        String PS3 = "month > ";
        String PS4 = "date > ";
        String PS5 = "message > ";
        String HELP = "1. 특정 날짜에 일정을 등록합니다." +
                "\n2. 특정 날짜에 일정이 등록되어 있는지 검색합니다." +
                "\n3. 현재 날짜의 이번 달 달력을 출력합니다." +
                "\n4. 특정 연도의 특정 달 달력을 검색합니다." +
                "\nh. 도움말을 출력합니다." +
                "\nq. 프로그램을 종료합니다.\n";
    }

    public Prompt() {
        this.scanner = new Scanner(System.in);
        this.calender = new Calender();
    }

    public void run() {
        char menu;
        String input;
        boolean isLoop = true;
        while (isLoop) {
            System.out.println(ENV.CONSOLE);
            System.out.print(ENV.PS1);

            input = scanner.nextLine();
            if (input.length() != 1) continue;

            menu = input.charAt(0);

            switch (menu) {
                case '1':
                    scheduleSign();
                    break;
                case '2':
                    searchSchedule();
                    break;
                case '3':
                    showTodayCalender();
                    break;
                case '4':
                    searchCalender();
                    break;
                case 'h':
                case 'H':
                    printHelp();
                    break;
                case 'q':
                case 'Q':
                    isLoop = false;
                    break;
            }
        }
        printMotd();
    }

    public void scheduleSign() throws DateTimeParseException {
        String date;
        String message;
        LocalDate localDate;

        System.out.println("[일정 등록] 날짜를 입력하세요.");
        System.out.print(ENV.PS4);
        date = scanner.nextLine();
        localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(Calender.FORMAT_PATTERN));

        System.out.println("[일정 등록] 일정을 입력하세요.");
        System.out.print(ENV.PS5);
        message = scanner.nextLine();

        calender.addSchedule(localDate, message);
        System.out.println("일정이 등록되었습니다.");
    }

    public void searchSchedule() throws DateTimeParseException {
        String date;

        System.out.println("[일정 검색] 날짜를 입력하세요.");
        System.out.print(ENV.PS4);
        date = scanner.nextLine();
        date = date.strip();

        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern(Calender.FORMAT_PATTERN));

        Optional<Schedule> schedule = calender.getSchedule(localDate);

        if (schedule.isEmpty()) {
            System.out.println("등록된 일정이 없습니다.");
            return;
        }
        System.out.println(schedule.get().showMessage());
    }

    public void showTodayCalender() {
        LocalDateTime now = LocalDateTime.now();
        calender.printCalender(now.getYear(), now.getMonthValue());
    }

    public void searchCalender() {
        System.out.println("년도를 입력하세요.");
        System.out.print(ENV.PS2);
        year = inputNumber();
        if (year == -1) return;

        System.out.println("달을 입력하세요");
        System.out.print(ENV.PS3);
        month = inputNumber();

        if (month == -1 || month < 1 || month > 12) return;

        calender.printCalender(year, month);
    }

    public void printHelp() {
        System.out.println(ENV.HELP);
    }

    public void printMotd() {
        System.out.println("Bye-!");
    }

    public int inputNumber() throws InputMismatchException {
        int inputNumber = scanner.nextInt();
        scanner.nextLine();
        return inputNumber;
    }
}
