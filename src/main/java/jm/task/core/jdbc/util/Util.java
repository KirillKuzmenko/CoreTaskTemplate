package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static Connection getConnection() throws SQLException,
            ClassNotFoundException {
        String hostName = "localhost";
        String dbName = "User";
        String username = "root";
        String password = "admin";

        return getConnection(hostName, dbName, username, password);
    }

    public static Connection getConnection(String hostName, String dbName,
                                           String username, String password)
            throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");

        String connectionURL = "jdbc:mysql://" + hostName + "/" + dbName
                + "?serverTimezone=Europe/Moscow&useSSL=false";

        return DriverManager.getConnection(connectionURL, username, password);
    }

}
