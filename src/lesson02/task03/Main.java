package lesson02.task03;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Задание 3. Дан массив объектов Person. Класс Person характеризуется полями age (возраст, целое число 0-100), sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка). Создать два класса, методы которых будут реализовывать сортировку объектов. Предусмотреть единый интерфейс для классов сортировки. Реализовать два различных метода сортировки этого массива по правилам:
 *
 * первые идут мужчины
 * выше в списке тот, кто более старший
 * имена сортируются по алфавиту
 *
 * Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 * Предусмотреть генерацию исходного массива (10000 элементов и более).
 * Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 */

public class Main {
    public static void main(String[] args) {
        /**
         * создаем список Person
         */
        List<Person> persons = new ArrayList<>();
        List<Person> personscopy = new ArrayList<>();
        /**
         * вводим количество персон
         */
        Scanner input = new Scanner(System.in);
        System.out.print("Количество персон: ");
        int cnt = input.nextInt();
        /**
         * случайным образом формируем список:
         */
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder names = new StringBuilder();
        /**
         * цикл по введенному количеству персон
         */
        for (int i = 0; i < cnt; i++) {
            /**
             * создаем случайное имя
             */
            names.setLength(0);
            names.append(Character.toUpperCase(alphabet.charAt(r.nextInt(alphabet.length()))));
            for (int j = 0; j < 5; j++) {
                names.append(alphabet.charAt(r.nextInt(alphabet.length())));
            }

            /**
             * добавляем в список персон человека со случайным возрастом, именем, полом
             */
            Person p = new Person(r.nextInt(100),                          //количество лет
                    names.toString(),                               // имя
                    Sex.values()[r.nextInt(Sex.values().length)]);  // пол
            persons.add(p);
            /**
             * создаем копию списка, чтобы потом сравнивать время сортировки у одного набора данных
             */
            personscopy.add(p);
        }

        /**
         * вызываем сортировку, сперва методом "вставки" (Insertion Sort) потом "пузырьком" (BubbleSort)
         * startTime - начальное время
         * stopTime - конечное время
         * periodTime - разница
         */
        InsertionSort isort = new InsertionSort();
        BubbleSort bsort = new BubbleSort();

        long startTime = System.currentTimeMillis();
        System.out.println("сортировка Insertion Sort");
        isort.sort(persons);
        long stopTime = System.currentTimeMillis();
        long periodTime = stopTime - startTime;
        System.out.println("время = " + periodTime);

        startTime = System.currentTimeMillis();
        System.out.println("сортировка BubbleSort");
        bsort.sort(personscopy);
        stopTime = System.currentTimeMillis();
        periodTime = stopTime - startTime;
        System.out.println("время = " + periodTime);

        /**
         *  выводим отсортированный список
         */
        System.out.println(persons);
    }
}

class MyException extends Exception {
}