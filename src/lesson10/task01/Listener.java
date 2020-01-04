package lesson10.task01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Listener extends Thread {
    private int portClient;
    private String name;

    public Listener(int portClient, String name) {

        this.portClient = portClient;
        this.name = name;
    }

    @Override
    public void run() {
        try (
                DatagramSocket clientSocket = new DatagramSocket(portClient);
        ) {
            String sendToServer = " ";
            while (!sendToServer.isEmpty()) {
                System.out.println("Введите текст сообщения: ");
                BufferedReader writerUser = new BufferedReader(new InputStreamReader(System.in));
                sendToServer = writerUser.readLine();

                if (sendToServer.equals("quit")) {
                    System.out.println(this.name + " нас покинул");
                    break;
                }

                byte[] sendToServerBytes = new byte[1024];
                sendToServerBytes = sendToServer.getBytes();
                InetAddress IPAddress = InetAddress.getByName(Server.DEFAULT_HOST);
                DatagramPacket messageClientPacket = new DatagramPacket(sendToServerBytes, sendToServerBytes.length, IPAddress, Server.SERVER_PORT);

                clientSocket.send(messageClientPacket);

                byte[] getFromServer = new byte[1024];
                DatagramPacket getFromPacketServer = new DatagramPacket(getFromServer, getFromServer.length);
                clientSocket.receive(getFromPacketServer);
                String receiveMessage = new String(getFromPacketServer.getData());

                System.out.println("От " + this.name+ " получено сообщение: " + receiveMessage);
            }
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}