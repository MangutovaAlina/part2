package lesson15;

import lesson15.connectionManager.ConnectionManager;
import lesson15.connectionManager.ConnectionManagerJdbcImpl;
import lesson15.dao.RoleDAO;
import lesson15.dao.TableDAO;
import lesson15.dao.UserDAO;
import lesson15.dao.UserRoleDAO;
import lesson15.pojo.Role;
import lesson15.pojo.User;
import lesson15.pojo.UserRole;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

/**
 * Задание:
 * 1) Спроектировать базу
 * Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
 * Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
 * Таблица USER_ROLE содержит поля id, user_id, role_id
 * Типы полей на ваше усмотрению, возможно использование VARCHAR(255)
 * <p>
 * 2) Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 * a) Используя параметризированный запрос
 * b) Используя batch процесс
 * 3) Сделать параметризированную выборку по login_ID и name одновременно
 * 4) Перевести connection в ручное управление транзакциями
 * a) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 * между sql операциями установить логическую точку сохранения(SAVEPOINT)
 * б) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 * между sql операциями установить точку сохранения (SAVEPOINT A),
 * намеренно ввести некорректные данные на последней операции, что бы транзакция откатилась к логической точке SAVEPOINT A
 */

public class Main {

    public static void main(String[] args) {
        final int COUNT_CLIENT = 10;

        List<String> nameList = Arrays.asList("Administration", "Clients", "Billing");
        Random r = new Random();

        try {
            ConnectionManager connectionManager = ConnectionManagerJdbcImpl.getInstance();
            Connection connection = connectionManager.getConnection();

            // заполняем таблицу Role, User, UserRole с помощью batch, параметризация есть прямо в нем
            List<Role> roles = new ArrayList<>();
            for (int i = 0; i < nameList.size(); i++) {
                Role role = new Role(i + 1, nameList.get(i), "description" + (i + 1));
                roles.add(role);
            }
            RoleDAO roleDao = new RoleDAO(connection);
            roleDao.addRowBatch(roles);

            List<User> users = new ArrayList<>();

            for (int i = 0; i < COUNT_CLIENT; i++) {
                String name = UtilsDataTest.randomCreateWord(UtilsDataTest.countchar);
                User user = new User(name, Date.valueOf(UtilsDataTest.randomCreateDate(UtilsDataTest.yearstart, UtilsDataTest.yearend)), name + "_id", name + "_city", name + "@mail.ru", "comment_" + name);
                users.add(user);
            }
            UserDAO userDao = new UserDAO(connection);
            userDao.addRowBatch(users);

            List<UserRole> userRoles = new ArrayList<>();
            users = userDao.getAll();
            for (User user : users) {
                UserRole userRole = new UserRole(user.getId(), 1 + r.nextInt(2));
                userRoles.add(userRole);
            }
            UserRoleDAO userRoleDao = new UserRoleDAO(connection);
            userRoleDao.addRowBatch(userRoles);

            // тестируем savepoint на примере таблицы User
            System.out.println(UtilsDataTest.ExampleSavePoint(connection));
        } catch (MyException e) {
            System.out.println(e.getErrorMessage());
        }
    }
}