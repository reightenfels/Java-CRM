package com.dev.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.time.Duration;


public class Http {
    private final String baseUrl = "http://127.0.0.1:8080";
    private final HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    private Builder requestBuilder;
    
    public Http(String url) {
        this.requestBuilder = HttpRequest.newBuilder().uri(URI.create(baseUrl + url));
        this.requestBuilder.header("Content-Type", "application/json");
    }

    public static Http request(String url) {
        return new Http(url);
    }

    public Http get() {
        requestBuilder.GET();
        return this;
    }

    public Http post() {
        requestBuilder.POST();
        return this;
    }

    public void send() {
        
    }
}
