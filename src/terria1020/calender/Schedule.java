package terria1020.calender;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Schedule {
    private LocalDate date;
    private String message;

    public Schedule(LocalDate date, String message) {
        this.date = date;
        this.message = message;
    }

    public Schedule(String date, String message) {
        this.date = LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern("yyyy-M-d")
        );
        this.message = message;
    }

    public void editMessage(String message) {
        if (this.message.equals(message)) return;
        this.message = this.message + "\n" + message;
    }

    public String showMessage() {
        return "일정: " + message;
    }

    public boolean isEquals(LocalDate localDate) {
        return this.date.equals(localDate);
    }

    public LocalDate getDate() {
        return this.date;
    }
}
