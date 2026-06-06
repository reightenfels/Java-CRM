package com.dev.services;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.Builder;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class Http {
    private final String baseUrl = "http://127.0.0.1:8080";
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    
    private final String path;
    private String httpMethod = "GET";
    private String requestBody = null;
    private boolean requiresAuth = false;
    
    private Consumer<HttpResponse<String>> responseHandler;
    private Consumer<Throwable> errorHandler;

    private final List<Map.Entry<String, String>> queryParams = new ArrayList<>();
    
    public Http(String url) {
        this.path = url;
    }

    public static Http request(String url) {
        return new Http(url);
    }

    public Http auth() {
        this.requiresAuth = true;
        return this;
    }

    public Http get() {
        this.httpMethod = "GET";
        return this;
    }

    public Http post(String json) {
        this.httpMethod = "POST";
        this.requestBody = json;
        return this;
    }

    public Http delete(String json) {
        this.httpMethod = "DELETE";
        this.requestBody = json;
        return this;
    }

    public Http queryParam(String name, String value) {
        this.queryParams.add(Map.entry(name, value));
        return this;
    }

    public Http onResponse(Consumer<HttpResponse<String>> handler) {
        this.responseHandler = handler;
        return this;
    }

    public Http onError(Consumer<Throwable> handler) {
        this.errorHandler = handler;
        return this;
    }

    public void send() {
        sendInternal(false);
    }
    
    private void sendInternal(boolean isRetry) {
        HttpRequest request = buildRequest();
        
        Consumer<HttpResponse<String>> handler = responseHandler != null 
                ? responseHandler 
                : resp -> System.out.println("Response: " + resp.body());

        Consumer<Throwable> errHandler = errorHandler != null 
                ? errorHandler 
                : ex -> System.err.println("Error: " + ex.getMessage());

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenAccept(resp -> {
                if (resp.statusCode() == 401 && requiresAuth && !isRetry) {
                    handleUnauthorized(errHandler);
                } else {
                    handler.accept(resp);
                }
            })
            .exceptionally(ex -> {
                errHandler.accept(ex);
                return null;
            });
    }
    
    private void handleUnauthorized(Consumer<Throwable> errHandler) {
        TokenManager.getInstance().refresh()
            .thenAccept(success -> {
                if (success) {
                    sendInternal(true);
                } else {
                    errHandler.accept(new RuntimeException("Token refresh failed"));
                }
            })
            .exceptionally(ex -> {
                errHandler.accept(new RuntimeException("Token refresh error: " + ex.getMessage(), ex));
                return null;
            });
    }
    
    private HttpRequest buildRequest() {
        String uriString = baseUrl + path;
        
        if (!queryParams.isEmpty()) {
            StringBuilder query = new StringBuilder();
            for (var param : queryParams) {
                if (!query.isEmpty()) query.append("&");
                String encodedName = URLEncoder.encode(param.getKey(), StandardCharsets.UTF_8);
                String encodedValue = URLEncoder.encode(param.getValue(), StandardCharsets.UTF_8);
                query.append(encodedName).append("=").append(encodedValue);
            }
            uriString += "?" + query;
        }
        
        Builder builder = HttpRequest.newBuilder()
            .uri(URI.create(uriString))
            .header("Content-Type", "application/json")
            .timeout(Duration.ofSeconds(10));
        
        if (requiresAuth) {
            String token = TokenManager.getInstance().getAccessToken();
            if (token != null && !token.isEmpty()) {
                builder.header("Authorization", "Bearer " + token);
            }
        }
        
        switch (httpMethod.toUpperCase()) {
            case "POST":
                builder.POST(HttpRequest.BodyPublishers.ofString(requestBody));
                break;
            case "PUT":
                builder.PUT(HttpRequest.BodyPublishers.ofString(requestBody));
                break;
            case "DELETE":
                 if (requestBody != null && !requestBody.isEmpty()) {
                    builder.method("DELETE", HttpRequest.BodyPublishers.ofString(requestBody));
                } else {
                    builder.DELETE();
                }
                break;
            case "GET":
            default:
                builder.GET();
                break;
        }
        
        return builder.build();
    }
}