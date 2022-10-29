package com.example.notificationservice.service.impl;

import com.example.notificationservice.service.GatewayService;
import org.springframework.stereotype.Service;

@Service
public class GatewayServiceImpl implements GatewayService {
    public void send(String userId, String message) {
        System.out.println("SENT: message to user " + userId + ": " + message);
    }
}
