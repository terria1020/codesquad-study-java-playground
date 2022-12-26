package terria1020.calender.dbconnecttion;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectorTest {

    DatabaseConnector connector;
    private static final String DB_PATH = "/Users/jaehan1346/IdeaProjects/codesquad-study-java-playground/database/schedule.sqlite";

    @Test
    public void connect() throws SQLException {
        connector = new DatabaseConnector(DB_PATH);

        assertEquals(
                connector.connect(),
                "success"
        );

        assertNotEquals(
                connector.getConn(),
                null
        );

        ResultSet resultSet = connector.getConn().createStatement().executeQuery("select * from schedule");
        assertEquals(
                resultSet.getString("schedule_date"),
                "2022-12-1"
        );

    }

}