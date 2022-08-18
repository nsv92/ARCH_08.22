package ru.geekbrains.domain;

import java.util.HashMap;

public class RequestBuilder {

    private final HttpRequest request;


    public RequestBuilder() {
        request = new HttpRequest();
    }

    public RequestBuilder method(String str) {
        request.setMethod(str);
        return this;
    }

    public RequestBuilder url(String str) {
        request.setUrl(str);
        return this;
    }

    public RequestBuilder headers(HashMap<String, String> map) {
        request.setHeaders(map);
        return this;
    }

    public RequestBuilder body(String str) {
        request.setBody(str);
        return this;
    }

    public HttpRequest build() {
        return request;
    }
}
