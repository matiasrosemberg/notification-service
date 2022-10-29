package com.example.notificationservice.service.impl;

import com.example.notificationservice.service.CounterService;
import com.example.notificationservice.service.GatewayService;
import com.example.notificationservice.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    public CounterService counterService;
    public GatewayService gatewayService;

    @Autowired
    public NotificationServiceImpl(CounterService counterService, GatewayService gatewayService) {
        this.counterService = counterService;
        this.gatewayService = gatewayService;
    }

    @Override
    public void send(String type, String userId, String message) {
        if (counterService.checkRates(type, userId)) {
            gatewayService.send(userId, message);
        } else {
            System.out.println("NOT SENT: type = " + type + ", userId = " + userId + ", message = " + message);
        }
    }

    @Scheduled(fixedRate = 1000)
    void sendSomeNotifications() {
        send("ads", "Matias", "This is an ad");
    }
}
