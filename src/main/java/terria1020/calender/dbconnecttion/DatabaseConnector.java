package terria1020.calender.dbconnecttion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    }

    public String connect() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection(JDBC_PATH + databasePath);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            return "success";
        }
        else return "already connected";
    }

    public Connection getConn() {
        return conn;
    }
}
