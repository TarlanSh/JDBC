package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserDao dao = new UserDaoJDBCImpl();
        dao.createUsersTable();
        System.out.println("Create  table Users");
        dao.saveUser("Ivan", "Ivanov", (byte) 35);
        dao.saveUser("Peter", "Petrov", (byte) 20);
        dao.saveUser("Maria", "Sidorova", (byte) 18);
        dao.saveUser("Olga", "Kozlova", (byte) 23);
        dao.getAllUsers();
        dao.cleanUsersTable();

        dao.dropUsersTable();
        System.out.println("Drop table Users");

    }
}
