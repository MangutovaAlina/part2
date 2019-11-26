package lesson07;

import java.math.BigInteger;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class Factorial {
    /**
     * создаем структуру для хранения подсчетов факториала
     * вида (число, факториал)
     */
    private static ConcurrentNavigableMap<Integer, BigInteger> factorialMap = new ConcurrentSkipListMap<>();

    public BigInteger calculationFactorial(int inputNumber) {
        int beginNumber = 1;
        int endNumber = inputNumber;
        BigInteger bigInteger = BigInteger.valueOf(beginNumber);

        /** найдем последнее рассчитанное число, чтобы убыстрить
         *  процесс рассчета
         *  поскольку массив отсортирован, по идее это нам сэкономит время
         *  но не все так просто!
         *  потокам пофиг, отсортирован ли массив :)
         */
        for (int i = endNumber; i > 0; i--) {
            if (factorialMap.containsKey(i)) {
                beginNumber = i;
                bigInteger = factorialMap.get(beginNumber);
                System.out.println("Последнее подсчитанное число " + beginNumber + " = " + bigInteger);
                break;
            }
        }

        /** считаем факториал
         */
        for (int i = beginNumber + 1; i <= endNumber; i++) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(i));
        }

        factorialMap.put(endNumber, bigInteger);
        System.out.println("Число = " + endNumber + " факториал = " + bigInteger);

        return bigInteger;
    }
}

