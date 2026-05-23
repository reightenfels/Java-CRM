package com.dev.dto.models;

public class TaskModel {
    private final String title;
    private final int price;
    private final String deadline;
    private final String managerLogin; 
    private final String clientLogin;

    public TaskModel(String title, int price, String deadline, String managerLogin, String clientLogin) {
        this.title = title;
        this.price = price;
        this.deadline = deadline;
        this.managerLogin = managerLogin;
        this.clientLogin = clientLogin;
    }

    public String getTitle() { return title; }
    public int getPrice() { return price; }
    public String getDeadline() { return deadline; }
    public String getManagerLogin() { return managerLogin; }
    public String getClientLogin() { return clientLogin; }
}
