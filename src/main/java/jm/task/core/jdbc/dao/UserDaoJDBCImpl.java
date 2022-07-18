package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        Connection connection = Util.getConnection();
        String sqlCommand = "CREATE TABLE IF NOT EXISTS Users (id INT PRIMARY KEY AUTO_INCREMENT," +
                " name VARCHAR(32),  lastName VARCHAR(32), age smallint)";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable() {
        Connection connection = Util.getConnection();
        String sqlCommand = "DROP TABLE IF EXISTS Users";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sqlCommand);
            System.out.println("Таблица удалена");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection connection = Util.getConnection();
        try (PreparedStatement preparedStatement =
                     connection.prepareStatement("INSERT INTO Users (name, lastName, age) VALUES( ?, ?, ?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void removeUserById(long id) {
        Connection connection = Util.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users WHERE id=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        Connection connection = Util.getConnection();
        List<User> usersList = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String SQL = "SELECT * FROM Users";
            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                usersList.add(user);
                System.out.println(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() {
        Connection connection = Util.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM Users")) {
            preparedStatement.executeUpdate();
            System.out.println("Таблица очищена");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
