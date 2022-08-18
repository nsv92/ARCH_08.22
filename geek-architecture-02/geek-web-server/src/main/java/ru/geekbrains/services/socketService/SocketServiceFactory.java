package ru.geekbrains.services.socketService;

import java.net.Socket;

public final class SocketServiceFactory {

    public static SocketService create(Socket socket) {
        return new SimpleSocketService(socket);
    }
}
