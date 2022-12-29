package terria1020.calender;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@AllArgsConstructor
@NoArgsConstructor
public class Schedule {
    private LocalDate date;
    private String message;

    public Schedule(String date, String message) {
        this.date = LocalDate.parse(
                date,
                DateTimeFormatter.ofPattern(Calender.FORMAT_PATTERN)
        );
        this.message = message;
    }

    public Schedule editMessage(String message) {
        if (!this.message.equals(message)) this.message = this.message + ", " + message;
        return this;
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
    public String getMessage() { return this.message; }
}
