package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class RequestParser {

    public HttpRequest parse(Deque<String> rawRequest) {

        HttpRequest request = new HttpRequest();

//        Из первой строки забираем метод и путь
        String[] methodAndPath = rawRequest.poll().split(" ");
        request.setMethod(methodAndPath[0]);
        request.setPath(methodAndPath[1]);

//        Далее забираем headers
        Map<String, String> headers = new HashMap<>();
        while (!rawRequest.isEmpty() && !rawRequest.getFirst().isBlank()) {
            String[] header = rawRequest.poll().split(": ", 2);
            headers.put(header[0], header[1]);
        }
        request.setHeaders(headers);

//        Затем проверяем, есть ли body (отделяется пустой строчкой),
//        если есть то забираем его
        if (rawRequest.size() > 1 && rawRequest.getFirst().isBlank()) {
            rawRequest.removeFirst();
            StringBuilder body = new StringBuilder();
            do {
                body.append(rawRequest.poll());
            } while (!rawRequest.isEmpty());
            request.setBody(body.toString());
        }

        return request;
    }
}
