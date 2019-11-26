package lesson04.task02;

/**
 * Создать класс ObjectBox, который будет хранить коллекцию Object.
 * У класса должен быть метод addObject, добавляющий объект в коллекцию.
 * У класса должен быть метод deleteObject, проверяющий наличие объекта в коллекции и при наличии удаляющий его.
 * Должен быть метод dump, выводящий содержимое коллекции в строку.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        Integer[] numberInt = new Integer[]{1, 2, 5, 6};
        Double[] numberDouble = new Double[]{0.9, 3.3, 0.8};
        String[] stringArray = new String[]{"start", "current", "end"};

        ObjectBox objectBox = new ObjectBox();

        /** добавяем объекты с помощью  addObject  */
        objectBox.addObject(numberInt);
        objectBox.addObject(numberDouble);
        objectBox.addObject(stringArray);

        /** выведем объекты ObjectBox с помощью dump()*/
        System.out.println("объекты ObjectBox : " + objectBox.dump());

        /** удалим объект с помощью deleteObject() */
        objectBox.deleteObject(numberDouble);

        /** выведем объекты ObjectBox с помощью dump() */
        System.out.println("вызываем dump(): " + objectBox.dump());
    }
}
