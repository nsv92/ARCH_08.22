package ru.geekbrains;

import ru.geekbrains.domain.HttpResponse;

import java.util.Map;

public class ResponseSerializer {

    public String serialize(HttpResponse response) {

        String httpVersion = "HTTP/1.1 ";
        String code200 = "200 OK";
        String code404 = "404 NOT_FOUND";
        String code500 = "500 INTERNAL_SERVER_ERROR";

        StringBuilder sb = new StringBuilder(httpVersion);
        switch (response.getStatusCode()) {
            case 200:
                sb.append(code200).append("\n");
                break;
            case 404:
                sb.append(code404).append("\n");
                break;
            case 500:
                sb.append(code500).append("\n");
                break;
        }
        if (response.getHeaders() != null) {
            Map<String, String> headers = response.getHeaders();
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
        }
        if (response.getBody() != null) {
            sb.append("\n").append(response.getBody());
        }

        return sb.toString();
    }
}
