package ru.geekbrains.handler;

import ru.geekbrains.domain.HttpRequest;

public interface MethodHandler {

    public void handle(HttpRequest request);
}
