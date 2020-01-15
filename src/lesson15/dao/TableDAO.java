package lesson15.dao;

import lesson15.MyException;

import java.util.List;
import java.util.Optional;

/**
 * универсальный интерфейс для DAO
 *
 * @param <T> класс (User, Role, UserRole, etc)
 */
public interface TableDAO<T> {

    public T addRow(T t) throws MyException;

    public T updateRow(T t) throws MyException;

    public boolean deleteRow(Integer id) throws MyException;

    public Optional<T> getByID(Integer id) throws MyException;

    public List<T> getAll() throws MyException;

    public boolean addRowBatch(List<T> listBatch) throws MyException;
}
