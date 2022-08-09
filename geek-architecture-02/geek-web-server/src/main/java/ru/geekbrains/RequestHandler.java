package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

import java.io.IOException;

public class RequestHandler implements Runnable {

    private final SocketService socketService;

    private final FileService fileService;

    private final RequestParser requestParser;

    private final ResponseSerializer responseSerializer;

    public RequestHandler(SocketService socketService, FileService fileService, RequestParser requestParser, ResponseSerializer responseSerializer) {
        this.socketService = socketService;
        this.fileService = fileService;
        this.requestParser = requestParser;
        this.responseSerializer = responseSerializer;
    }

    @Override
    public void run() {

        HttpRequest request = requestParser.parse(socketService.readRequest());


        if (!fileService.exists(request.getPath())) {
            HttpResponse response = new HttpResponse(404, "Content-Type", "text/html; charset=utf-8", "<h1>Файл не найден!</h1>");
            socketService.writeResponse(responseSerializer.serialize(response));
            return;
        }

        String body = fileService.readFile(request.getPath());
        HttpResponse response = new HttpResponse(200, "Content-Type", "text/html; charset=utf-8", body);
        socketService.writeResponse(responseSerializer.serialize(response));

        try {
            socketService.close();
        } catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
        System.out.println("Client disconnected!");
    }
}
