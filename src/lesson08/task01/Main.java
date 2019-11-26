package lesson08.task01;

/**
 * Задание 1. Необходимо разработать класс, реализующий следующие методы:
 * void serialize (Object object, String file);
 * Object deSerialize(String file);
 * Методы выполняют сериализацию объекта Object в файл file и десериализацию объекта из этого файла.
 * Обязательна сериализация и десериализация "плоских" объектов (все поля объекта - примитивы, или String).
 */

public class Main {

    public static void main(String[] args) {
        final String fileName = "C:\\tmp_inn\\lesson08\\myObject.dat";   // путь до файла

        SerializeClass serialisation = new SerializeClass();

        /** записывание данных в поток
         */
        MyObject myObject = new MyObject(fileName, 5, 1.7, true);

        /** чтение ранее сериализованных данных из потока
         */
        serialisation.serialize(myObject, fileName);
        MyObject deObject = (MyObject) serialisation.deSerialize(fileName);

        /** поскольку поле booleanField у нас transient,
         * то при выводе на экран будет false, хотя выше в поток передавалась true
         */
        System.out.println(deObject.toString());
    }
}
