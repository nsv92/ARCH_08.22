package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.service.FileService;
import ru.geekbrains.service.SocketService;

public final class MethodHandlerFactory {

    public static MethodHandler create(SocketService socketService, ResponseSerializer responseSerializer, FileService fileService) {
        PutMethodHandler putHandler = new PutMethodHandler(null, socketService, responseSerializer,fileService);
        PostMethodHandler postHandler = new PostMethodHandler(putHandler, socketService, responseSerializer, fileService);
        return new GetMethodHandler(postHandler, socketService, responseSerializer, fileService);
    }
}
