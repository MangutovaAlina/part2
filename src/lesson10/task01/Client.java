package lesson10.task01;

import java.io.IOException;

public class Client {
    Client(String nameClient, int CLIENT_PORT) throws IOException {
        /**
         *  отправка + получение посредством Listener*/
        Listener listenerThread = new Listener(CLIENT_PORT, nameClient);
        /**
         * отдельный поток для отдельного клиента */
        listenerThread.start();
    }
}