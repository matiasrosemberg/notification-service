package com.example.notificationservice.service;

public interface NotificationService {
    void send(String type, String userId, String message);
}
