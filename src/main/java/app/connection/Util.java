package app.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String url = "jdbc:mysql://localhost:3306/flights?useSSL=false&serverTimezone=UTC";
    public static final String user ="root";
    public static final String password = "root";
    public java.sql.Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url,user,password);
            System.out.println("Connection accept");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Conection EROOR");
        }
        return connection;
    }
}
