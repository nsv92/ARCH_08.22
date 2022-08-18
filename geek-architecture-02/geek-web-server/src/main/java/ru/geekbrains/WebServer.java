package ru.geekbrains;

import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.config.ServerConfigFactory;
import ru.geekbrains.fileService.FIleServiceFactory;
import ru.geekbrains.service.SocketService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {

    public static void main(String[] args) {
        ServerConfig config = ServerConfigFactory.create(args);
        try (ServerSocket serverSocket = new ServerSocket(config.getPort())) {
            System.out.println("Server started!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected!");

                new Thread(new RequestHandler(
                        new SocketService(socket),
                        FIleServiceFactory.create(config.getWww()),
                        new RequestParser(),
                        new ResponseSerializer()
                )).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
