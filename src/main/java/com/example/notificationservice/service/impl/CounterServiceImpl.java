package com.example.notificationservice.service.impl;

import com.example.notificationservice.model.Counter;
import com.example.notificationservice.repository.CounterRepository;
import com.example.notificationservice.service.CounterService;

public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;

    public CounterServiceImpl(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Override
    public void increment(String type, String userId) {
        Counter counter = counterRepository.findById(userId).orElse(new Counter(userId));
        counter.increment(type);
        counterRepository.save(counter);
    }

    @Override
    public int get(String type, String userId) {
        Counter counter = counterRepository.findById(userId).orElse(new Counter(userId));
        return counter.get(type);
    }

}
}
