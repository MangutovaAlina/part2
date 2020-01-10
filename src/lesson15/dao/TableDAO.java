package lesson15.dao;

import lesson15.connectionManager.ConnectionManager;
import lesson15.connectionManager.ConnectionManagerJdbcImpl;

import java.util.List;
import java.util.Optional;

/**
 * универсальный интерфейс для DAO
 * @param <T> класс (User, Role, UserRole, etc)
 */
public interface TableDAO<T> {
    public static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();

    public boolean addRow(T t);

    public boolean updateRow(T t);

    public boolean deleteRow(Integer id);

    public Optional<T> getByID(Integer id);

    public List<T> getAll();

    public boolean addRowBatch(List<T> listBatch);
}
