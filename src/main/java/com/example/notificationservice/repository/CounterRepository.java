package com.example.notificationservice.repository;

import com.example.notificationservice.model.UserRate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CounterRepository {

    private final RedisTemplate<String, UserRate> redisTemplate;

    public CounterRepository(RedisTemplate<String, UserRate> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public UserRate findById(String id) {
        return redisTemplate.opsForValue().get(id);
    }

    public void save(UserRate userRate) {
        redisTemplate.opsForValue().set(userRate.getUserId(), userRate);
    }
}
