package lesson04.task03;

/**
 * Задание 3. Доработать классы MathBox и ObjectBox таким образом, чтобы MathBox был наследником ObjectBox.
 * Необходимо сделать такую связь, правильно распределить поля и методы.
 * Функциональность в целом должна сохраниться. При попытке положить Object в MathBox должно создаваться исключение.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        /** заполняем массив Number */
        Integer[] numberInt = new Integer[]{1, 2, 5, 6};

        MathBox mathBox = new MathBox(numberInt);

        /** вызываем метод dump для класса MathBox */
        System.out.println("dump - вывод в строчку массива: " + mathBox.dump());

        /** выводим сумму элементов numberList класса MathBox */
        System.out.println("сумма = " + mathBox.summator());

        /** удаляем из numberList класса MathBox элемент со значением 5 */
        System.out.println("удаляем элемент 5");
        mathBox.deleteObject(5);
        System.out.println("dump - вывод в строчку финального массива: " + mathBox.dump());

        /** вызываем исключение для добавления */
        try {
            if (mathBox.addObject(3) || !(mathBox.addObject(3))) {
                System.out.println("исключение при добавлении");
                exceptionShow();
            }
        } catch (MyException e) {
            e.printStackTrace();
        }
    }

    public static void exceptionShow() throws Exception {
        throw new MyException();
    }
}