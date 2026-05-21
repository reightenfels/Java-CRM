package com.dev.dto.models;

public class TaskModel {
    private final String title;
    private final int price;
    private final String date;
    private final String managerLogin; 
    private final String clientLogin;

    public TaskModel(String title, int price, String date, String managerLogin, String clientLogin) {
        this.title = title;
        this.price = price;
        this.date = date;
        this.managerLogin = managerLogin;
        this.clientLogin = clientLogin;
    }

    public String getTitle() { return title; }
    public int getPrice() { return price; }
    public String getDate() { return date; }
    public String getManagerLogin() { return managerLogin; }
    public String getClientLogin() { return clientLogin; }
}
