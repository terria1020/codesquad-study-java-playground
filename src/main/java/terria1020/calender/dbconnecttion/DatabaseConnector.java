package terria1020.calender.dbconnecttion;

import java.io.File;
import java.io.IOException;
import java.sql.*;

public class DatabaseConnector {
    private String databasePath;
    private static final String JDBC_PATH = "jdbc:sqlite:";
    Connection conn;

    public DatabaseConnector(String databasePath) {

        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        this.databasePath = databasePath;
        conn = null;

        File dbFile = new File(databasePath);
        if (!dbFile.exists()) init(dbFile);
    }

    private void init(File file) {
        try {
            file.createNewFile();
            connect();
            Statement statement = conn.createStatement();
            statement.execute(
                    "create table schedule (" +
                            "id integer primary key autoincrement, " +
                            "schedule_date text, " +
                            "message text" +
                            ")"
            );
            disconnect();
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public String connect() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(JDBC_PATH + databasePath);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "success";
        } else return "already connected";
    }

    public Connection getConn() {
        return conn;
    }

    public void disconnect() {
        try {
            conn.close();
            conn = null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
