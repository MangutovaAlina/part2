package lesson15;

import java.sql.*;
import java.util.List;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** недописанный класс для создания универсального инсерта через рефлексию
 */
public class InsertRow {

    public String insertRow(Class classTable, String nameTable) throws NoSuchMethodException {
        String string = "insert into " + nameTable + "(";

        Field[] declaredFields = classTable.getDeclaredFields();

        /** получаем колонки
         */
        int j = 0;
        for (Field field : declaredFields) {
            string = string + field.getName();
            if (j != declaredFields.length-1) {
                string = string + ", ";
            }
            j++;
        }
        string = string + ") values (";

        /**  aормируем строку запроса - values         *
         */
        j=0;
        for (Field field : declaredFields) {
            Method methodDin = classTable.getMethod("get" + field.getName());
        }

        return string;
    }

}

