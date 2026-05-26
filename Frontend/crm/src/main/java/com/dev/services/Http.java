package com.dev.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.function.Consumer;

public class Http {
    private final String baseUrl = "http://127.0.0.1:8080";
    private final HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(10)).build();
    private final Builder requestBuilder;
    private Consumer<HttpResponse<String>> responseHandler;
    private Consumer<Throwable> errorHandler;
    
    public Http(String url) {
        this.requestBuilder = HttpRequest.newBuilder().uri(URI.create(baseUrl + url));
        this.requestBuilder.header("Content-Type", "application/json");
    }

    public static Http request(String url) {
        return new Http(url);
    }

    public Http auth() {
        String accessToken = JwtTokens.accessToken;
        requestBuilder.header("Authorization", "Bearer " + accessToken);
        return this;
    }

    public Http get() {
        requestBuilder.GET();
        return this;
    }

    public Http post(String json) {
        requestBuilder.POST(HttpRequest.BodyPublishers.ofString(json));
        return this;
    }

    public Http onResponse(Consumer<HttpResponse<String>> handler) {
        this.responseHandler = handler;
        return this;
    }

    public void send() {
        Consumer<HttpResponse<String>> handler = responseHandler != null 
                ? responseHandler 
                : resp -> System.out.println("Response: " + resp.body());

        Consumer<Throwable> errHandler = errorHandler != null 
                ? errorHandler 
                : ex -> System.err.println("Error: " + ex.getMessage());

        client.sendAsync(requestBuilder.build(), HttpResponse.BodyHandlers.ofString())
        .thenAccept(handler)
        .exceptionally(ex -> {
            errHandler.accept(ex);

            return null;
        });
    }
}
