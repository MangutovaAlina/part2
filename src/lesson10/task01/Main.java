package lesson10.task01;

import java.io.IOException;

/**
 * Задание 1. Разработать приложение - многопользовательский чат, в котором участвует произвольное количество клиентов.
 * Каждый клиент после запуска отправляет свое имя серверу. После чего начинает отправлять ему сообщения.
 * Каждое сообщение сервер подписывает именем клиента и рассылает всем клиентам (broadcast).
 *
 * из Задания 2. ради удобства добавлен способ выхода из чата с помощью написанной в чате команды «quit»
 */
public class Main {
    public static void main(String[] args) throws IOException {
        Client client1 = new Client("Alex", 4998);
        System.out.println("Alex" + " входит в чат");
        Client client2 = new Client("Masha", 4997);
        System.out.println("Masha" + " входит в чат");
        Client client3 = new Client("Vasya", 4996);
        System.out.println("Vasya" + " входит в чат");
    }
}
