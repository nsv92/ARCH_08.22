package ru.geekbrains.handler;

import ru.geekbrains.ResponseSerializer;
import ru.geekbrains.config.ServerConfig;
import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.HttpResponse;
import ru.geekbrains.service.SocketService;

abstract class MethodHandlerImpl implements MethodHandler {

    private final String method;

    private final MethodHandlerImpl next;

    protected final SocketService socketService;

    protected final ResponseSerializer responseSerializer;

    protected final ServerConfig serverConfig;

    public MethodHandlerImpl(String method, MethodHandlerImpl next, SocketService socketService, ResponseSerializer responseSerializer, ServerConfig serverConfig) {
        this.method = method;
        this.next = next;
        this.socketService = socketService;
        this.responseSerializer = responseSerializer;
        this.serverConfig = serverConfig;
    }

    @Override
    public void handle(HttpRequest request) {

        HttpResponse response;
        if (method.equals(request.getMethod())) {
            response = handleInternal(request);
        } else if (next != null) {
            next.handle(request);
            return;
        } else {
            response = HttpResponse.createBuilder()
                    .withStatusCode(405)
                    .withStatusCodeName("METHOD_NOT_ALLOWED")
                    .withHeader("Content-Type", "text/html; charset=ytf-8")
                    .withBody("<h1>Метод не поддерживается!</h1>")
                    .build();
        }
        String rawResponse = responseSerializer.serialize(response);
        socketService.writeResponse(rawResponse);
    }

    protected abstract HttpResponse handleInternal(HttpRequest request);
}
