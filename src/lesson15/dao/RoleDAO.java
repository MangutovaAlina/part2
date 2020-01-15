package lesson15.dao;

import lesson15.Main;
import lesson15.MyException;
import lesson15.pojo.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.*;

/**
 * реализация DAO для класса Role таблицы Role
 */
public class RoleDAO implements TableDAO<Role> {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(Main.class);

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
            logger.info("addRow in Role:" + role.toString());
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
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
            logger.info("updateRow Role:" + role.toString());
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
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
            logger.info("deleteRow from Role id=" + id);
            return true;
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
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
            logger.info("select from Role where id=" + id);
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
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
            }
            logger.info("select all from Role");
            return roles;
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
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
            logger.info("insert in Role " + listBatch.size() + " strings");
            insertBatch.executeBatch();
            return true;
        } catch (SQLException e) {
            logger.error(Level.ERROR, new Throwable(e.getMessage()));
            throw new MyException(e.getMessage());
        }
    }
}
