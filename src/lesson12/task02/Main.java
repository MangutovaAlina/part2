package lesson12.task02;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/** Задание 2.
 *
 * Доработать программу так, чтобы ошибка OutOfMemoryError возникала в Metaspace /Permanent Generation
 */

public class Main {
    public static void main(String[] args) {
        int cnt = 50000;
        List<String> list = new ArrayList<>();

        System.out.println("тотальная память: " + Runtime.getRuntime().totalMemory());
        System.out.println("максимальная память: " + Runtime.getRuntime().maxMemory());
        System.out.println("свободная память: " + Runtime.getRuntime().freeMemory());


        /** создаем длинный стринг и помещаем в лист
         *  если там встречается i удаляем
         */
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        String word = "";
        for (int i = 0; i < cnt; i++) {
            word = word + alphabet.charAt(r.nextInt(alphabet.length()));
        }

        while (true) {
            System.out.println(word.substring(r.nextInt(99),100).intern());
            list.add(word.substring(r.nextInt(99),100).intern());
        }
    }
}
