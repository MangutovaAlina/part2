package lesson15.dao;

import lesson15.MyException;
import lesson15.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * реализация DAO для класса Role таблицы Role
 */
public class RoleDAO implements TableDAO<Role> {
    private Connection connection;

    public RoleDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Role addRow(Role role) throws MyException {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "INSERT INTO \"InnBD\".\"ROLE\" (id, name, description) VALUES (?, ?::\"InnBD\".enum_name, ?);");
            preparedStatement.setInt(1, role.getId());
            preparedStatement.setString(2, role.getName());
            preparedStatement.setString(3, role.getDescription());
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                role.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
        return role;
    }

    @Override
    public Role updateRow(Role role) throws MyException {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "UPDATE \"InnBD\".\"ROLE\" SET name=?::\"InnBD\".enum_name, description=? " +
                            "WHERE id=?;");
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setInt(3, role.getId());
            System.out.println("изменяем " + preparedStatement.executeUpdate() + " строк");
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
        return role;
    }

    @Override
    public boolean deleteRow(Integer id) throws MyException {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "DELETE FROM \"InnBD\".\"ROLE\" WHERE id=?;");
            preparedStatement.setInt(1, id);
            System.out.println("удаляем " + preparedStatement.executeUpdate() + " строк");
            return true;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public Optional<Role> getByID(Integer id) throws MyException {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "SELECT id, name, description FROM  \"InnBD\".\"ROLE\" WHERE id = ?;");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Role role = new Role(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
                return Optional.of(role);
            }
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Role> getAll() throws MyException {
        List<Role> roles = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "SELECT id, name, description FROM  \"InnBD\".\"ROLE\";");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Role role = new Role(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3));
                roles.add(role);
                System.out.println(role.toString());
            }
            return roles;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }

    @Override
    public boolean addRowBatch(List<Role> listBatch) throws MyException {
        try {
            PreparedStatement insertBatch = this.connection.prepareStatement(
                    "INSERT INTO \"InnBD\".\"ROLE\" (id, name, description) VALUES(?, ?::\"InnBD\".enum_name, ?);");

            for (int i = 0; i < listBatch.size(); i++) {
                Role role = listBatch.get(i);
                insertBatch.setInt(1, role.getId());
                insertBatch.setString(2, role.getName());
                insertBatch.setString(3, role.getDescription());
                insertBatch.addBatch();
            }
            System.out.println("заносим в Role " + listBatch.size() + " строк");
            insertBatch.executeBatch();
            return true;
        } catch (SQLException e) {
            throw new MyException(e.getMessage());
        }
    }
}
