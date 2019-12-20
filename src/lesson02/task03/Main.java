package lesson02.task03;

import java.lang.Exception;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * Задание 3. Дан массив объектов Person. Класс Person характеризуется полями age (возраст, целое число 0-100), sex (пол – объект класса Sex со строковыми константами внутри MAN, WOMAN), name (имя - строка). Создать два класса, методы которых будут реализовывать сортировку объектов. Предусмотреть единый интерфейс для классов сортировки. Реализовать два различных метода сортировки этого массива по правилам:
 * <p>
 * первые идут мужчины
 * выше в списке тот, кто более старший
 * имена сортируются по алфавиту
 * <p>
 * Программа должна вывести на экран отсортированный список и время работы каждого алгоритма сортировки.
 * Предусмотреть генерацию исходного массива (10000 элементов и более).
 * Если имена людей и возраст совпадают, выбрасывать в программе пользовательское исключение.
 */

public class Main {
    public static void main(String[] args) {
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
         * создаем список Person
         */
        List<Person> persons = new ArrayList<>();

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
            Person p = new Person(r.nextInt(100),            //количество лет
                    names.toString(),                               // имя
                    Sex.values()[r.nextInt(Sex.values().length)]);  // пол

            boolean addperson = true;

            try {
                for (int j = 0; j < persons.size(); j++) {
                    if (p.equals(persons.get(j))) {
                        throw new MyException();
                    }
                }

            } catch (MyException e) {
                addperson = false;
                System.out.println("MyException: персоны совпадают!");
            }

            if (addperson) {
                persons.add(p);
            }


        }

        /**
         * вызываем сортировку, сперва методом "вставки" (Insertion Sort) потом "пузырьком" (BubbleSort)
         * startTime - начальное время
         * stopTime - конечное время
         * periodTime - разница
         */

        List<SortInterface> intfsort = new ArrayList<>();

        intfsort.add(new InsertionSort());
        intfsort.add(new BubbleSort());

        for (int i = 0; i < intfsort.size(); i++) {
            long startTime = System.currentTimeMillis();
            System.out.println("сортировка " + i);
            SortInterface sortinterface = intfsort.get(i);

            List<Person> personscopy = new ArrayList<>(persons);

            sortinterface.sort(personscopy);
            long stopTime = System.currentTimeMillis();
            long periodTime = stopTime - startTime;
            System.out.println("время = " + periodTime);

            System.out.println(personscopy);
        }
    }
}

class MyException extends Exception {
}