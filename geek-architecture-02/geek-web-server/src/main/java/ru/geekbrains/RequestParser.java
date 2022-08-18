package ru.geekbrains;

import ru.geekbrains.domain.HttpRequest;
import ru.geekbrains.domain.RequestBuilder;

import java.util.Deque;
import java.util.HashMap;

public class RequestParser {

    public HttpRequest parse(Deque<String> rawRequest) {

        String[] firstLine = rawRequest.pollFirst().split(" ");
        HashMap<String, String> headers = getHeaders(rawRequest);
        String body = getBody(rawRequest);

        return new RequestBuilder()
                .method(firstLine[0])
                .url(firstLine[1])
                .headers(headers)
                .body(body)
                .build();
    }

    private HashMap<String, String> getHeaders(Deque<String> rawRequest) {
        HashMap<String, String> headers = new HashMap<>();
        while (!rawRequest.isEmpty()) {
            String line = rawRequest.pollFirst();
            if (line.isBlank()) {
                break;
            }
            String[] header = line.split(": ");
            headers.put(header[0], header[1]);
        }
        return headers;
    }

    private String getBody(Deque<String> rawRequest) {
        StringBuilder sb = new StringBuilder();
        while (!rawRequest.isEmpty()) {
            sb.append(rawRequest.pollFirst());
        }
        return sb.toString();
    }
}
