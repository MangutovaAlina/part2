package lesson02.task01;

import java.lang.Exception;

/**
 * Задание 1. Написать программу ”Hello, World!”. В ходе выполнения программы она должна выбросить исключение и завершиться с ошибкой.
 *
 * Смоделировав ошибку «NullPointerException»
 * Смоделировав ошибку «ArrayIndexOutOfBoundsException»
 * Вызвав свой вариант ошибки через оператор throw
 */

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello world!");

        /** вызовем последовательно три ошибки
         *  для чего в цикле вызываем метод exc01
         *  с параметром i - номером ошибки
         */
        for (int i = 0; i < 3; i++) {
            try {
                exceptionThree(i);
            } catch (NullPointerException e) {
                e.printStackTrace();
            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
            } catch (MyException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * метод генерирует три вида исключений в заивисмости от параметра
     *
     * @param i номер ошибки
     * @throws NullPointerException           вызывается при i=0
     * @throws ArrayIndexOutOfBoundsException вызывается при i=1
     * @throws Exception                      вызывается при i=2
     */
    public static void exceptionThree(int i) throws NullPointerException, ArrayIndexOutOfBoundsException, Exception {
        if (i == 0) {
            String s1 = null;
            String s2 = s1.toLowerCase();
        }
        if (i == 1) {
            int[] j = new int[3];
            j[5] = 7;
            ;
        }
        if (i == 2)
            throw new MyException();
    }

}

class MyException extends Exception {
}
