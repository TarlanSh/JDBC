package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static  Util instance;
    static String url =  "jdbc:mysql://localhost:3306/my_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
            "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String URLFIXED =
            "jdbc:mysql://localhost:3306/my_db?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true" +
                    "&useLegacyDatetimeCode=false&serverTimezone=UTC";
    static String username = "root";
    static String password = "tbsh";
    private static  SessionFactory sessionFactory;

    private Util(){}

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URLFIXED, username, password);
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
    private static final Properties settings = new Properties();
    static {
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, url);
        settings.put(Environment.USER, username);
        settings.put(Environment.PASS, password);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL,"true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
    }
    public static SessionFactory getSessionFactory() {
        try {
            Configuration configuration = new Configuration();
            configuration.setProperties(settings);
            configuration.addAnnotatedClass(User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sessionFactory;
    }
    public static Util getInstance(){
        if (instance == null){
            instance = new Util();
        }
        return instance;
    }

}