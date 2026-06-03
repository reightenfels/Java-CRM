package com.dev.services;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.locks.ReentrantLock;

import com.dev.dto.responses.success.LoginResponse;

public class TokenManager {
    private static final TokenManager INSTANCE = new TokenManager();
    
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private final String baseUrl = "http://127.0.0.1:8080";
    
    private volatile String accessToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlbXBsb3llZTEiLCJpYXQiOjE3ODA0Mjg2OTAsImV4cCI6MTc4MDQ2NDY5MH0.6NguVmCTRMx0WBwMUW0ZF9iJRQ8KT6RlP9xQLbaDRlg";
    private volatile String refreshToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyMiIsImlhdCI6MTc3OTc3Mzg2MCwiZXhwIjoxNzgwMDI1ODYwfQ.YwYA4pmAdPJYfV5qyQkwwGqJYFWKW7V4UDuXnT-NMi0";
    
    private final ReentrantLock refreshLock = new ReentrantLock();
    private volatile CompletableFuture<Boolean> activeRefresh = null;
    
    private TokenManager() {}
    
    public static TokenManager getInstance() {
        return INSTANCE;
    }
    
    public void initTokens(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
    
    public String getAccessToken() {
        return accessToken;
    }
    
    public String getRefreshToken() {
        return refreshToken;
    }
    
    public CompletableFuture<Boolean> refresh() {
        CompletableFuture<Boolean> current = activeRefresh;
        if (current != null && !current.isDone()) {
            return current;
        }
        
        if (refreshLock.tryLock()) {
            try {
                if (activeRefresh != null && !activeRefresh.isDone()) {
                    return activeRefresh;
                }
                
                CompletableFuture<Boolean> newRefresh = new CompletableFuture<>();
                activeRefresh = newRefresh;
                
                doRefreshAsync(newRefresh);
                
                return newRefresh;
            } finally {
                refreshLock.unlock();
            }
        } else {
            return activeRefresh != null 
                    ? activeRefresh 
                    : CompletableFuture.completedFuture(false);
        }
    }
    
    private void doRefreshAsync(CompletableFuture<Boolean> result) {
        String currentRefresh = this.refreshToken;
        if (currentRefresh == null || currentRefresh.isEmpty()) {
            cleanupRefresh(result);
            result.completeExceptionally(new RuntimeException("No refresh token available"));
            return;
        }
        
        String payload = String.format("{\"refreshToken\":\"%s\"}", escapeJson(currentRefresh));
        
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/api/auth/refresh-token"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + refreshToken)
                .POST(HttpRequest.BodyPublishers.ofString(payload))
                .build();
        
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
            .thenAccept(resp -> {
                try {
                    if (resp.statusCode() == 200) {
                        String body = resp.body();

                        LoginResponse registerResponse = ResponseUnpacker.extract(body, LoginResponse.class);

                        String newAccess = registerResponse.accessToken;
                        String newRefresh = registerResponse.refreshToken;
                        
                        if (newAccess != null) {
                            this.accessToken = newAccess;
                            if (newRefresh != null) {
                                this.refreshToken = newRefresh;
                            }
                            result.complete(true);
                        } else {
                            result.completeExceptionally(
                                new RuntimeException("Failed to parse tokens from response"));
                        }
                    } else {
                        result.completeExceptionally(new RuntimeException(
                            "Refresh failed with status: " + resp.statusCode()));
                    }
                } catch (Exception e) {
                    result.completeExceptionally(e);
                } finally {
                    cleanupRefresh(result);
                }
            })
            .exceptionally(ex -> {
                result.completeExceptionally(
                    new RuntimeException("Refresh request error: " + ex.getMessage(), ex));
                cleanupRefresh(result);
                return null;
            });
    }
    
    private void cleanupRefresh(CompletableFuture<Boolean> completed) {
        if (activeRefresh == completed) {
            activeRefresh = null;
        }
    }
    
    private String escapeJson(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }
}