package lesson15;

import java.sql.*;
import java.util.List;

/** класс для параметризированного Insert
 */
public class InsertRowParam {
    /** параметризированная вставка в таблицу     *
     *  @param strQuery - строка запроса
     *  @param stringValue - список значений
     *  @param stringType - список типов
     *  @param connection - коннект
     *  @throws SQLException
     */
    public static void insertRowParam(String strQuery, String table, List<String> stringValue, List<String> stringType, Connection connection) throws SQLException {
        try (PreparedStatement insert = connection.prepareStatement(strQuery)) {

            /** получаем id и заносим его в insert строку
             */
            PreparedStatement selectID = connection.prepareStatement(
                    "SELECT max(id) id FROM " + table);
            ResultSet resultSet = selectID.executeQuery();
            int id = 0;
            while (resultSet.next()) {
                id = resultSet.getInt("id");
            }
            id++;
            insert.setInt(1, id);

            /** заполняем остальные поля
             */
            for (int i = 0; i < stringValue.size(); i++) {
                if (stringType.get(i).equals("int")) {
                    insert.setInt(i + 2, Integer.parseInt(stringValue.get(i)));
                }

                if (stringType.get(i).equals("str")) {
                    insert.setString(i + 2, stringValue.get(i));
                }

                if (stringType.get(i).equals("dat")) {
                    insert.setDate(i + 2, java.sql.Date.valueOf(stringValue.get(i)));
                }
            }
            insert.execute();
        }
    }

}


