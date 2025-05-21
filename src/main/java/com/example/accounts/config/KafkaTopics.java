package com.example.accounts.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KafkaTopics {
    private Map<String, String> topics;
}
