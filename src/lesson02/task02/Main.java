package lesson02.task02;

import java.util.Random;
import java.util.Scanner;
import java.lang.Exception;

/**
 * Задание 2. Составить программу, генерирующую N случайных чисел. Для каждого числа k вычислить квадратный корень q.
 * Если квадрат целой части q числа равен k, то вывести это число на экран.
 * Предусмотреть что первоначальные числа могут быть отрицательные, в этом случае генерировать исключение.
 */

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Количество: ");
        int cnt = input.nextInt();

        Random random = new Random();
        int num;
        double sqroot;

        for (int i = 0; i < cnt + 1; i++) {
            /** получаем случайное число, в том числе отрицательное */
            num = (random.nextInt(65536) - 32768);

            try {
                if (num < 0)
                    throw new MyException();
                else {
                    sqroot = Math.sqrt(num);
                    // round
                    if (Math.pow((long) sqroot, 2) == num)
                        System.out.println("Число = " + num + "  корень = " + sqroot);
                }
            } catch (MyException e) {
                System.out.println("Отрицательное число = " + num);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

class MyException extends Exception {
}

