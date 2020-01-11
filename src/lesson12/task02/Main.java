package lesson12.task02;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * Задание 2.
 * <p>
 * Доработать программу так, чтобы ошибка OutOfMemoryError возникала в Metaspace /Permanent Generation
 */

public class Main {

    //рекомендация: создать сотни тысяч прокси классов

    public static void main(String[] args) {

        System.out.println("тотальная память: " + Runtime.getRuntime().totalMemory());
        System.out.println("максимальная память: " + Runtime.getRuntime().maxMemory());
        System.out.println("свободная память: " + Runtime.getRuntime().freeMemory());

        List<Person> listPerson = new ArrayList<>();
        int count = 0;
        while (true) {
            Man man = new Man(30 + count, "Vasya" + count);

            ClassLoader manClassLoader = man.getClass().getClassLoader();
            Class[] interfaces = man.getClass().getInterfaces();
            Person proxyVasia = (Person) Proxy.newProxyInstance(manClassLoader, interfaces, new PersonInvocationHandler(man));
            proxyVasia.personHello(man.getName());

            listPerson.add(proxyVasia);
            count++;
        }
    }
}
