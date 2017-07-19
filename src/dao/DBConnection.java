package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by EVA-08 on 2017/7/6.
 */

public class DBConnection {
    private static final String username = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://localhost:3306/marketdb";
    private static ThreadLocal<Connection> connectionHolder = new ThreadLocal<>();

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() {
        Connection connection = connectionHolder.get();
        if (connection == null) {
            try {
                connectionHolder.set(DriverManager.getConnection(url, username, password));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connectionHolder.get();
    }

    void disconnect() {
        try {
            connectionHolder.get().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
