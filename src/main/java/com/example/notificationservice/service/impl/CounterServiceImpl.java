package com.example.notificationservice.service.impl;

import com.example.notificationservice.configuration.MessageRatesConfig;
import com.example.notificationservice.model.UserRate;
import com.example.notificationservice.repository.CounterRepository;
import com.example.notificationservice.service.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CounterServiceImpl implements CounterService {

    private final CounterRepository counterRepository;
    private final MessageRatesConfig messageRatesConfig;

    @Autowired
    public CounterServiceImpl(CounterRepository counterRepository, MessageRatesConfig messageRatesConfig) {
        this.counterRepository = counterRepository;
        this.messageRatesConfig = messageRatesConfig;
    }

    @Override
    public Boolean checkRates(String type, String userId) {
        UserRate userRate = counterRepository.findById(userId);
        if (userRate == null) {
            userRate = new UserRate();
            userRate.setUserId(userId);
            counterRepository.save(userRate);
        }
        userRate.increment(type, messageRatesConfig.getWindowPerType().get(type));

        if (validateRates(type, userRate)) {
            return false;
        }

        counterRepository.save(userRate);
        return true;
    }

    private boolean validateRates(String type, UserRate userRate) {
        return userRate.getTypeCounters().get(type).getCount() > messageRatesConfig.getMaxPerType().get(type) ||
                userRate.getTypeCounters().get(type).getCreatedAt() + messageRatesConfig.getWindowPerType().get(type) < System.currentTimeMillis();
    }
}
