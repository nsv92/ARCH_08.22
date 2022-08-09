package ru.geekbrains;

import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    private static Properties properties;

    static {
        try {
            properties = new Properties();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        try (ServerSocket serverSocket = new ServerSocket(properties.getPort())) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                new Thread(new RequestHandler(new SocketService(socket), new FileService(properties.getRootDir()),
                        new RequestParser(), new ResponseSerializer())).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
