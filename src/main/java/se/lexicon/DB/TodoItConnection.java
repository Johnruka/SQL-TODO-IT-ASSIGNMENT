package se.lexicon.DB;

import se.lexicon.exception.DBConnectionException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TodoItConnection {

    private static final String DB_NAME = "todo_it_sql_db";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/" + DB_NAME;
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "78888";


    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (SQLException e) {
            throw new DBConnectionException("Failed to connect to DB.");
        }
    }
}
