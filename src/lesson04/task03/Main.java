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

        /** выводим сумму элементов numberList класса MathBox */
        System.out.println("сумма = " + mathBox.summator());

        /** удаляем из numberList класса MathBox элемент со значением 5 */
        try {
            mathBox.addObject(3);
            mathBox.addObject(4);
        } catch (MyException e) {
            e.printStackTrace();
        }
        /** выводим numberList класса MathBox*/
        System.out.println("список:\n");
        System.out.println(mathBox.toString());

        /** вызываем метод dump для класса MathBox */
        mathBox.dump(mathBox.getNumbers());
    }
}