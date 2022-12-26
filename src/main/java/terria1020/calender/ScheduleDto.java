package terria1020.calender;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ScheduleDto {

    LocalDate date;
    String message;

    public ScheduleDto(ResultSet resultSet) throws SQLException {
        this.date = LocalDate.parse(resultSet.getString("schedule_date"),
                DateTimeFormatter.ofPattern(Calender.FORMAT_PATTERN)
        );
        this.message = resultSet.getString("message");
    }

    public Schedule toSchedule() {
        return new Schedule(this.date, this.message);
    }
}
