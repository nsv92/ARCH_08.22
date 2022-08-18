package ru.geekbrains.domain;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class RequestBuilderTest {

    @Test
    public void RequestBuilderTesting() {
        HashMap<String, String> testMap = new HashMap<>();
        testMap.put("key", "value");
        HttpRequest request = new HttpRequest("method", "url", testMap, "body");

        HttpRequest requestFromBuilder = new RequestBuilder()
                .method("method")
                .url("url")
                .headers(testMap)
                .body("body")
                .build();

        Assert.assertEquals(request.getMethod(), requestFromBuilder.getMethod());
        Assert.assertEquals(request.getUrl(), requestFromBuilder.getUrl());
        Assert.assertEquals(request.getHeaders(), requestFromBuilder.getHeaders());
        Assert.assertEquals(request.getBody(), requestFromBuilder.getBody());
    }
}
