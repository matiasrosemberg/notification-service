package com.example.notificationservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash("UserRate")
public class UserRate implements Serializable {

    @Id
    private String userId;
    private Map<String, Counter> typeCounters;

    public void increment(String type, long limit) {
        if (typeCounters == null) {
            typeCounters = new HashMap<>();
        }
        if (typeCounters.containsKey(type)) {
            Counter counter = typeCounters.get(type);
            if (counter.getCreatedAt() + limit < System.currentTimeMillis()) {
                counter.setCount(1);
                counter.setCreatedAt(System.currentTimeMillis());
            } else {
                counter.setCount(counter.getCount() + 1);
            }
        } else {
            typeCounters.put(type, new Counter(1, System.currentTimeMillis()));
        }

    }

}
