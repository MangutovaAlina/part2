package lesson08.task02;

/**
 * Задание 2. Предусмотреть работу c любыми типами полей (полями могут быть ссылочные типы).
 * Требование: Использовать готовые реализации (Jaxb, jackson и т.д.) запрещается.
 */
public class Main {
    public static void main(String[] args) {
        final String fileName = "C:\\tmp_inn\\lesson08\\myPerson.dat";   // путь до файла
        SerializeClass serialisation = new SerializeClass();

        /** записывание данных в поток
         */
        Person myPerson = new Person(38, "Petr", Sex.MAN);

        /** чтение ранее сериализованных данных из потока
         */
        serialisation.serialize(myPerson, fileName);
        Person dePerson = (Person) serialisation.deSerialize(fileName);

        /** поскольку поле booleanField у нас transient,
         * то при выводе на экран будет false, хотя выше в поток передавалась true
         */
        System.out.println(dePerson.toString());
    }
}

