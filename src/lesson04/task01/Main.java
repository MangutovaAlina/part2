package lesson04.task01;

/**
 * Написать класс MathBox, реализующий следующий функционал:
 * <p>
 * Конструктор на вход получает массив Number. Элементы не могут повторяться. Элементы массива внутри объекта раскладываются в подходящую коллекцию (выбрать самостоятельно).
 * Существует метод summator, возвращающий сумму всех элементов коллекции.
 * Существует метод splitter, выполняющий поочередное деление всех хранящихся в объекте элементов на делитель, являющийся аргументом метода. Хранящиеся в объекте данные полностью заменяются результатами деления.
 * Необходимо правильно переопределить методы toString, hashCode, equals, чтобы можно было использовать MathBox для вывода данных на экран и хранение объектов этого класса в коллекциях (например, hashMap). Выполнение контракта обязательно!
 * Создать метод, который получает на вход Integer и если такое значение есть в коллекции, удаляет его.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        /** заполняем массив Number */
        Number[] number = new Number[]{0.1, 2, 5, 6.2, 3, 1, 0.9, 3.3, 0, 8, 0x55, 0x55aa};

        MathBox mathBox = new MathBox(number);

        System.out.println("начальный массив: " + mathBox.toString());

        /** выводим сумму элементов numberList класса MathBox */
        System.out.println("сумма элементов = " + mathBox.summator());

        /** удаляем из numberList класса MathBox элемент со значением 5 */
        mathBox.deleteList(5);
        System.out.println("после удаления элемента 5: " + mathBox.toString());

        /** делим элементы numberList класса MathBox на число */

        System.out.println("после деления на 3: " + mathBox.splitter(3).toString());

        /** получаем хэшкод */
        System.out.println("хэшкод: " + mathBox.hashCode());

    }
}
