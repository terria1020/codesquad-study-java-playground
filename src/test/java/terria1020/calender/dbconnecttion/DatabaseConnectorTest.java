package terria1020.calender.dbconnecttion;

import org.junit.After;
import org.junit.Test;

import java.io.File;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseConnectorTest {

    DatabaseConnector connector;
    private static final String DB_PATH = "schedule.sqlite";

    @Test
    public void connect() throws SQLException {
        connector = new DatabaseConnector();

        assertEquals(
                connector.connect(),
                "success"
        );

        assertNotEquals(
                connector.getConn(),
                null
        );
    }

    @After
    public void tearDown() throws Exception {
        connector = null;
        File file = new File(DB_PATH);
        if (file.exists()) file.delete();
    }

    @Test
    public void init() {
        connector = new DatabaseConnector();
        File file = new File(DB_PATH);
        assertEquals(file.exists(), true);

    }

}