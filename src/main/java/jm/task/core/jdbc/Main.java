package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao dao = new UserDaoJDBCImpl();
        UserService userService = new UserServiceImpl(dao);
       // d rjycnhernj cthdbcfк в конструктор сервиса
        userService.createUsersTable();
        System.out.println("Create  table Users");
        userService.saveUser("Ivan", "Ivanov", (byte) 35);
        userService.saveUser("Peter", "Petrov", (byte) 20);
        userService.saveUser("Maria", "Sidorova", (byte) 18);
        userService.saveUser("Olga", "Kozlova", (byte) 23);
        userService.getAllUsers();
        userService.cleanUsersTable();

        userService.dropUsersTable();
        System.out.println("Drop table Users");

    }
}
