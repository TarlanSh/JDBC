package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД

    private static Util instance;

    private Util(){}

    public static Util getInstance(){
        if(instance == null){
            instance = new Util();
        }
        return instance;
    }

    public Connection getConnection(){
        String url =  "jdbc:mysql://localhost:3306/my_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                "&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username = "root";
        String password = "tbsh";

        try {
            return DriverManager.getConnection(url, username, password);
        }catch (SQLException e){
            throw new RuntimeException("Ошибка соединения с БД",e);
        }
    }
}