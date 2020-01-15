package lesson15.dao;

import lesson15.Main;
import lesson15.MyException;
import lesson15.pojo.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.*;

/**
 * реализация DAO для класса User таблицы User
 */
public class UserDAO implements TableDAO<User> {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(Main.class);

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public User addRow(User user) throws MyException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"InnBD\".\"USER\" (id, name, \"login_ID\", city, birthday, email, description) VALUES (DEFAULT, ?, ?, ?, ?, ?, ?);");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLogin_id());
            preparedStatement.setString(3, user.getCity());
            preparedStatement.setDate(4, user.getBirthday());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getDescription());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                user.setId(resultSet.getInt("id"));
            }
            logger.info("addRow in User:" + user.toString());
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
            throw new MyException(e.getMessage());
        }
        return user;
    }

    @Override
    public User updateRow(User user) throws MyException {
        try {
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
            logger.info("updateRow User:" + user.toString());
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
            throw new MyException(e.getMessage());
        }
        return user;
    }

    @Override
    public boolean deleteRow(Integer id) throws MyException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM \"InnBD\".\"USER\" WHERE id=?;");
            preparedStatement.setInt(1, id);
            logger.info("deleteRow from User id=" + id);
            return true;
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public Optional<User> getByID(Integer id) throws MyException {
        try {
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
                return Optional.of(user);
            }
            logger.info("select from User where id=" + id);
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
            throw new MyException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<User> getAll() throws MyException {
        List<User> users = new ArrayList<>();
        try {
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
            }
            logger.info("select all from User");
            return users;
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean addRowBatch(List<User> listBatch) throws MyException {
        try {
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
            logger.info("insert in User " + listBatch.size() + " strings");
            insertBatch.executeBatch();
            return true;
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
            throw new MyException(e.getMessage());
        }
    }
}
