package lesson12.task01;

import java.util.ArrayList;
import java.util.List;

/** Задание 1.
 *
 *  Необходимо создать программу, которая продемонстрирует утечку памяти в Java.
 *  При этом объекты должны не только создаваться, но и периодически частично удаляться, чтобы GC имел возможность очищать часть памяти.
 *  Через некоторое время программа должна завершиться с ошибкой OutOfMemoryError c пометкой Java Heap Space.
 */

public class Main {
    public static void main(String[] args) {
        String [] string = new String[]{"ooioo", "ooofo", "oofff", "ofoii", "oooio", "iffoo", "ofoof", "ooioi", "ofoio", "foiff"};
        List<String> list = new ArrayList<>();

        for (int i = 0; i < string.length-1; i++)  {
            list.add(string[i]);
        }

        System.out.println("тотальная память: " + Runtime.getRuntime().totalMemory());
        System.out.println("максимальная память: " + Runtime.getRuntime().maxMemory());
        System.out.println("свободная память: " + Runtime.getRuntime().freeMemory());


        /** идем по листу,
         *  если встретится в слове буква f удаляем слово
         *  если встретится в слове буква i перемещаем слово вперед
         *
         */
        for (int i=0; i<list.size()-1; i++) {
            if (list.get(i).contains("i")) {
                list.remove(i);
            }

            if (list.get(i).contains("f")) {
                String  current = list.get(i);
                list.add((i+1), current);
            }
        }


    }
}

