package com.example.notificationservice.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class UserRateTest {

    @Test
    void testIncrementNonExistingType() {
        UserRate userRate = new UserRate();
        userRate.setUserId("1");
        userRate.increment("type", 5000);

        Assertions.assertEquals(1, userRate.getTypeCounters().size());
        Assertions.assertEquals(1, userRate.getTypeCounters().get("type").getCount());
    }

    @Test
    void testIncrementExistingTypeOk() {
        long createdAt = System.currentTimeMillis();
        UserRate userRate = new UserRate();
        userRate.setUserId("1");
        Counter counter = new Counter(1, createdAt);
        HashMap<String, Counter> typeCounters = new HashMap<>();
        typeCounters.put("type", counter);
        userRate.setTypeCounters(typeCounters);
        userRate.increment("type", 5000);

        Assertions.assertEquals(1, userRate.getTypeCounters().size());
        Assertions.assertEquals(2, userRate.getTypeCounters().get("type").getCount());
        Assertions.assertEquals(createdAt, userRate.getTypeCounters().get("type").getCreatedAt());
    }

    @Test
    void testIncrementExistingTypeGetsShortOfTimeAndRestarts() {
        long createdAt = System.currentTimeMillis() - 5000;
        UserRate userRate = new UserRate();
        userRate.setUserId("1");
        Counter counter = new Counter(1, createdAt);
        HashMap<String, Counter> typeCounters = new HashMap<>();
        typeCounters.put("type", counter);
        userRate.setTypeCounters(typeCounters);
        userRate.increment("type", 3000);

        Assertions.assertEquals(1, userRate.getTypeCounters().size());
        Assertions.assertEquals(1, userRate.getTypeCounters().get("type").getCount());
        Assertions.assertNotEquals(createdAt, userRate.getTypeCounters().get("type").getCreatedAt());
    }

}