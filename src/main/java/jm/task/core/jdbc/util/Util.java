package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    static String url =  "jdbc:mysql://localhost:3306/my_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static String username = "root";
    static String password = "tbsh";
    private Util(){}

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с БД",e);
        }
    }
    public static void closeConnection(){
        try {
            getConnection().close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}