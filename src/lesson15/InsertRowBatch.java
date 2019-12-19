package lesson15;

import java.sql.*;
import java.util.List;

/** класс для BatchInsert
 */
public class InsertRowBatch {

    /**  заносим в базу с помощью batch     *
     *  @param strQuery - строка запроса
     *  @param stringValue - список значений
     *  @param stringType - список типов
     *  @param connection - коннект
     *  @throws SQLException
     */
    public static void insertRowBatch(String strQuery, String table, List<String> stringValue, List<String> stringType, Connection connection) throws SQLException {

        try (PreparedStatement insertBatch = connection.prepareStatement(strQuery)) {
            /** получаем максимальный id и заносим его в insert строку
             */
            PreparedStatement selectID = connection.prepareStatement(
                    "SELECT max(id) id FROM " + table);
            ResultSet resultSet = selectID.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            id++;

            /** заносим 10 строчек
             */
            for (int j = 0; j < 10; j++) {
                id = id + j + 1;
                insertBatch.setInt(1, id);

                for (int i = 0; i < stringValue.size(); i++) {
                    if (stringType.get(i).equals("int")) {
                        insertBatch.setInt(i + 2, Integer.parseInt(stringValue.get(i)));
                    }

                    if (stringType.get(i).equals("str")) {
                        insertBatch.setString(i + 2, stringValue.get(i) + Integer.toString(id));
                    }

                    if (stringType.get(i).equals("dat")) {
                        insertBatch.setDate(i + 2, Date.valueOf(stringValue.get(i)));
                    }
                }
                insertBatch.addBatch();
            }
            insertBatch.executeBatch();
        }
    }
}


