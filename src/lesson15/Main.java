package lesson15;

import java.sql.*;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

/**  Задание:
 *   1) Спроектировать базу
 *       Таблица USER содержит поля id, name, birthday, login_ID, city, email, description
 *       Таблица ROLE содержит поля id, name (принимает значения Administration, Clients, Billing), description
 *       Таблица USER_ROLE содержит поля id, user_id, role_id
 *       Типы полей на ваше усмотрению, возможно использование VARCHAR(255)
 *
 *   2) Через jdbc интерфейс сделать запись данных(INSERT) в таблицу
 *       a) Используя параметризированный запрос
 *       b) Используя batch процесс
 *   3) Сделать параметризированную выборку по login_ID и name одновременно
 *   4) Перевести connection в ручное управление транзакциями
 *       a) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 *            между sql операциями установить логическую точку сохранения(SAVEPOINT)
 *       б) Выполнить 2-3 SQL операции на ваше усмотрение (например, Insert в 3 таблицы – USER, ROLE, USER_ROLE)
 *            между sql операциями установить точку сохранения (SAVEPOINT A),
 *            намеренно ввести некорректные данные на последней операции, что бы транзакция откатилась к логической точке SAVEPOINT A
 */

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, NoSuchMethodException {
        /** коннектимся к базе
         */
        Connection connection = new ConnectDB().connectDB();

        /** лист перечислений и рандом для него
         */
        List<EnumName> nameList = Arrays.asList(EnumName.Administration, EnumName.Clients, EnumName.Billing);
        Random r = new Random();

        if (connection != null) {
            /** лист значений для Insert
             */
            List<String> stringValue;

            /** лист типов наших таблиц
             */
            List<String> stringTypeRole = Arrays.asList("str");
            List<String> stringTypeUser = Arrays.asList("str", "int", "str", "dat", "str", "str");
            List<String> stringTypeUserRole = Arrays.asList("int", "int", "int");

            /**  пример простого INSERT
             */
            String strQueryUser = "INSERT INTO \"InnBD\".\"USER\" (id, name, \"login_ID\", city, birthday, email, description) VALUES (1, 'Vasya', 3, 'Kazan', '1986-01-01', 'vasya_kazan@inbox.ru', 'учится в Innopolis');";
            try (PreparedStatement insert = connection.prepareStatement(strQueryUser)) {
                insert.execute();
            } catch(SQLException e){
                System.out.println(e);
            }

            /** параметризированные insert'ы и установка логической точки сохранения(SAVEPOINT)
             *  используем параметризацию
             *  для этого убираем автокоммин
             */
            connection.setAutoCommit(false);

            /** для строки инсерта роли случайно выбираем enum
             */
            EnumName name = nameList.get(r.nextInt(3));
            String strQueryRole = "INSERT INTO \"InnBD\".\"ROLE\" (id, name, description) VALUES (?,";

            if (name.equals(EnumName.Administration)) {
                strQueryRole = strQueryRole + "'Administration', ?);";
            }
            if (name.equals(EnumName.Clients)) {
                strQueryRole = strQueryRole + "'Clients', ?);";
            }
            if (name.equals(EnumName.Billing)) {
                strQueryRole = strQueryRole + "'Billing', ?);";
            }

            strQueryUser = "INSERT INTO \"InnBD\".\"USER\" (id, name, \"login_ID\", city, birthday, email, description) VALUES (?, ?, ?, ?, ?, ?, ?);";

            /** лист значений
             */
            ListStringValue listValue = new ListStringValue();

            /** устанавливаем savepoint
             */
            Savepoint savepoint = connection.setSavepoint("savepoint");
            try {
                InsertRowParam insertParam = new InsertRowParam();

                /** пример параметризированного insert
                 */
                stringValue = listValue.listStringValue(stringTypeUser, "USER");
                insertParam.insertRowParam(strQueryUser, "\"InnBD\".\"USER\"", stringValue, stringTypeUser, connection);
                stringValue = listValue.listStringValue(stringTypeRole, "ROLE");
                insertParam.insertRowParam(strQueryRole, "\"InnBD\".\"ROLE\"",  stringValue, stringTypeRole, connection);

                connection.commit();
            } catch(SQLException e){
                connection.rollback(savepoint);
                System.out.println("откатились к savepoint" + e);
            }

            /**  пример простого INSERT а также моделирование ошибки (если запустить второй раз)
             */
            String strQueryUserRole = "INSERT INTO \"InnBD\".\"USER_ROLE\" (id, user_id, role_id) VALUES (1, 1, 1);";

            Savepoint savepointErr = connection.setSavepoint("savepointErr");
            try (PreparedStatement insert = connection.prepareStatement(strQueryUserRole)) {
                insert.execute();
                connection.commit();
            } catch(SQLException e){
                connection.rollback(savepointErr);
                System.out.println("откатились к savepointErr" + e);
            }

            /** INSERT Batch и установка логической точки сохранения(SAVEPOINT)
             */
            Savepoint savepointA = connection.setSavepoint("savepointA");
            try {
                InsertRowBatch insertParamBatch = new InsertRowBatch();

                stringValue = listValue.listStringValue(stringTypeUser, "USER");
                insertParamBatch.insertRowBatch(strQueryUser, "\"InnBD\".\"USER\"", stringValue, stringTypeUser, connection);
                connection.commit();
            } catch(SQLException e) {
                connection.rollback(savepointA);
                System.out.println("откатились к savepointA " + e);
            }

            /** параметризированная выборка по login_ID и name одновременно
             */
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM \"InnBD\".\"USER\" WHERE \"login_ID\" = ? and name = ?");
            preparedStatement.setInt(1, 3);
            preparedStatement.setString(2, "Vasya");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.print("login_ID = " + resultSet.getString("login_ID") +
                        ", name = " + resultSet.getString("name"));
            }

            /** а тут я просто игралась с возможностью сделать универсальный запрос через reflect
             *  было бы интересно создать имитацию таблицы: этакий список объектов типа Role или User или UserRole
             *  заполнить их, ну например из другой баз или файла и вот таким образом перенести в наши таблицы
             *  но просто времени уже нет на эту фигню
             */
            //InsertRow insertRow = new InsertRow();

           // Role role = new Role(1, EnumName.Administration, "комментарий");
           // String string = insertRow.insertRow(Role.class, "Role");

            //System.out.print("string = " + string);

            /** закрываем коннект
             */
            connection.close();
        }
    }
}

