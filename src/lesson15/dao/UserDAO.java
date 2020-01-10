package lesson15.dao;

import lesson15.pojo.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *  реализация DAO для класса User таблицы User
 */
public class UserDAO implements TableDAO<User> {
    @Override
    public boolean addRow(User user) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"InnBD\".\"USER\" (id, name, \"login_ID\", city, birthday, email, description) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin_id());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setDate(4, user.getBirthday());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDescription());
            System.out.println("добавляем " + preparedStatement.executeUpdate() + " строк");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean updateRow(User user) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE \"InnBD\".\"USER\" SET name=?, " +
                            "\"login_ID\"=?" +
                            "city=?" +
                            "birthday=?" +
                            "email=?" +
                            "description=?" +
                            "WHERE id=?;");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin_id());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setDate(4, user.getBirthday());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDescription());
            preparedStatement.setInt(7, user.getId());
            System.out.println("изменяем " + preparedStatement.executeUpdate() + " строк");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteRow(Integer id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM \"InnBD\".\"USER\" WHERE id=?;");
            preparedStatement.setInt(1, id);
            System.out.println("удаляем " + preparedStatement.executeUpdate() + " строк");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> getByID(Integer id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, birthday, \"login_ID\", city, email, description FROM  \"InnBD\".\"USER\" WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new User(
                         resultSet.getInt(1),
                         resultSet.getString(2),
                         resultSet.getDate(3),
                         resultSet.getString(4),
                         resultSet.getString(5),
                         resultSet.getString(6),
                         resultSet.getString(7));
                return Optional.ofNullable(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, name, birthday, \"login_ID\", city, email, description FROM  \"InnBD\".\"USER\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDate(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7));
                users.add(user);
                System.out.println(user.toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    @Override
    public boolean addRowBatch(List<User> listBatch) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement insertBatch = connection.prepareStatement(
                    "INSERT INTO \"InnBD\".\"USER\" (id, name, \"login_ID\", city, birthday, email, description) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);");

            for (int i = 0; i < listBatch.size(); i++) {
                User user = listBatch.get(i);
                insertBatch.setString(1, user.getName());
                insertBatch.setString(2, user.getLogin_id());
                insertBatch.setString(3, user.getCity());
                insertBatch.setDate(4, user.getBirthday());
                insertBatch.setString(5, user.getEmail());
                insertBatch.setString(6, user.getDescription());
                insertBatch.addBatch();
            }
            System.out.println("заносим в User " + listBatch.size() + " строк");
            insertBatch.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
