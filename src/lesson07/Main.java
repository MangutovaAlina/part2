package lesson07;

import java.math.BigInteger;
import java.util.*;
import java.util.concurrent.*;

/**
 * Дан массив случайных чисел. Написать программу для вычисления факториалов всех элементов массива.
 * Использовать пул потоков для решения задачи.
 * <p>
 * Особенности выполнения:
 * <p>
 * Для данного примера использовать рекурсию - не очень хороший вариант,
 * т.к. происходит большое выделение памяти, очень вероятен StackOverFlow.
 * Лучше перемножать числа в простом цикле при этом создавать объект типа BigInteger
 * <p>
 * По сути, есть несколько способа решения задания:
 * <p>
 * 1) распараллеливать вычисление факториала для одного числа
 * 2) распараллеливать вычисления для разных чисел
 * 3) комбинированный
 * <p>
 * При чем вычислив факториал для одного числа, можно запомнить эти данные
 * и использовать их для вычисления другого, что будет гораздо быстрее
 */

public class Main {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /** создаем массив из 5 случайных числел от 0 до 50
         */
        List<Integer> listInteger = new ArrayList<>();

        Random random = new Random();
        int i = 1;
        while (i < 10) {
            int randomInteger = random.nextInt(i * 10);
            if (randomInteger != 0) {
                listInteger.add(randomInteger);
                i++;
            }
        }
        /** сортируем лист, потому что факториал так будет считаться быстрее
         */
        listInteger.sort(Comparator.naturalOrder());

        /** будем параллелить вычисления для разных чисел
         */
        ExecutorService threadExPool = Executors.newFixedThreadPool(listInteger.size() - 1);
        Factorial factorial = new Factorial();
        long startTime = System.currentTimeMillis();

        List<Future<BigInteger>> futures = new ArrayList<>();

        /** несмотря на то, что лист отсортирован,
         *  потоки высчитываются независимо и ситуация,
         *  когда бОльшее число в итоге считается раньше
         *  прекрасно иллюстрирует это!
         */
        for (i = 0; i < listInteger.size(); i++) {
            int number = listInteger.get(i);
            System.out.println("поток для рассчета числа = " + number);
            futures.add(CompletableFuture.supplyAsync(() -> factorial.calculationFactorial(number), threadExPool));
        }

        threadExPool.shutdown();

        System.out.println(" Время = " + (System.currentTimeMillis() - startTime));

    }
}


