package com.example.notificationservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties("message-rates")
@Data
public class MessageRatesConfig {
    private Map<String, Integer> maxPerType;
    private Map<String, Long> windowPerType;
}
