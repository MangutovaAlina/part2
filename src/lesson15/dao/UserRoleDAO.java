package lesson15.dao;

import lesson15.MyException;
import lesson15.pojo.UserRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * реализация DAO для класса UserRole таблицы UserRole
 */
public class UserRoleDAO implements TableDAO<UserRole> {
    private Connection connection;

    public UserRoleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public UserRole addRow(UserRole userRole) throws MyException {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO \"InnBD\".\"USER_ROLE\" (id, user_id, role_id) VALUES (DEFAULT, ?, ?);");
            preparedStatement.setInt(1, userRole.getRole_id());
            preparedStatement.setInt(2, userRole.getUser_id());
            System.out.println("добавляем " + preparedStatement.executeUpdate() + " строк");
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
        return userRole;
    }

    @Override
    public UserRole updateRow(UserRole userRole) throws MyException  {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE \"InnBD\".\"USER_ROLE\" SET user_id=?, " + "role_id=?" +
                            "WHERE id=?;");
            preparedStatement.setInt(1, userRole.getRole_id());
            preparedStatement.setInt(2, userRole.getUser_id());
            preparedStatement.setInt(2, userRole.getId());
            System.out.println("изменяем " + preparedStatement.executeUpdate() + " строк");
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
        return userRole;
    }

    @Override
    public boolean deleteRow(Integer id) throws MyException  {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM \"InnBD\".\"USER_ROLE\" WHERE id=?;");
            preparedStatement.setInt(1, id);
            System.out.println("удаляем " + preparedStatement.executeUpdate() + " строк");
            return true;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public Optional<UserRole> getByID(Integer id) throws MyException  {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, user_id, role_id FROM  \"InnBD\".\"USER_ROLE\" WHERE id=?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UserRole userRole = new UserRole(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
                return Optional.ofNullable(userRole);
            }
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<UserRole> getAll() throws MyException  {
        List<UserRole> userRoles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT id, user_id, role_id FROM  \"InnBD\".\"USER_ROLE\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                UserRole userRole = new UserRole(
                        resultSet.getInt(1),
                        resultSet.getInt(2),
                        resultSet.getInt(3));
                userRoles.add(userRole);
                System.out.println(userRole.toString());
            }
            return userRoles;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean addRowBatch(List<UserRole> listBatch) throws MyException  {
        try {
            PreparedStatement insertBatch = connection.prepareStatement(
                    "INSERT INTO \"InnBD\".\"USER_ROLE\" (id, user_id, role_id) VALUES (DEFAULT, ?, ?);");

            for (int i = 0; i < listBatch.size(); i++) {
                UserRole userRole = listBatch.get(i);
                insertBatch.setInt(1, userRole.getUser_id());
                insertBatch.setInt(2, userRole.getRole_id());
                insertBatch.addBatch();
            }
            System.out.println("заносим в UserRole " + listBatch.size() + " строк");
            insertBatch.executeBatch();
            return true;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }
}
