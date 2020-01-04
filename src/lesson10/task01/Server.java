package lesson10.task01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    public static final Integer SERVER_PORT = 4999;
    public static final String DEFAULT_HOST = "127.0.0.1";

    public static void main(String[] args) throws IOException {

        /**
         * получаем имя клиента сервером*/
        DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);
        byte[] messageFromClient = new byte[1024];

        DatagramPacket messageFromClientPacket = new DatagramPacket(messageFromClient, messageFromClient.length);

        serverSocket.receive(messageFromClientPacket);
        int port = messageFromClientPacket.getPort();
        InetAddress IPAddress = messageFromClientPacket.getAddress();
        String stringFromClient = new String(messageFromClientPacket.getData());
        System.out.println("На сервер пришло от клиента: " + stringFromClient);

        /**
         *  рассылаем */
        while (true) {
            messageFromClient = new byte[1024];
            messageFromClientPacket = new DatagramPacket(messageFromClient, messageFromClient.length);

            serverSocket.receive(messageFromClientPacket);
            port = messageFromClientPacket.getPort();
            IPAddress = messageFromClientPacket.getAddress();
            stringFromClient = new String(messageFromClientPacket.getData());
            System.out.println("На сервер пришло от клиента: " + stringFromClient);

            byte[] sendMessage = stringFromClient.getBytes();
            DatagramPacket sendMessagePacket = new DatagramPacket(sendMessage, sendMessage.length, IPAddress, port);

            serverSocket.send(sendMessagePacket);
        }
    }
}