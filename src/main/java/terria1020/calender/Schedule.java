package terria1020.calender;

import java.time.LocalDate;

public class Schedule {
    private LocalDate date;
    private String message;

    public Schedule(LocalDate date, String message) {
        this.date = date;
        this.message = message;
    }

    public Schedule editMessage(String message) {
        if (!this.message.equals(message)) this.message = this.message + ", " + message;
        return this;
    }

    public String showMessage() {
        return "일정: " + message;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getMessage() {
        return this.message;
    }
}
