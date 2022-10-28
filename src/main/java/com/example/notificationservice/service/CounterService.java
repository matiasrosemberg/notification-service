package com.example.notificationservice.service;

public interface CounterService {

    void increment(String type, String userId);

    int get(String type, String userId);

}
