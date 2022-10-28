package com.example.notificationservice.service.impl;

import com.example.notificationservice.service.NotificationService;

public class NotificationServiceImpl implements NotificationService {
    @Override
    public void send(String type, String userId, String message) {
        System.out.println("type = " + type + ", userId = " + userId + ", message = " + message);
    }
}
