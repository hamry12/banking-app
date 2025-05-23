package com.example.notification.config;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@ConfigurationProperties(prefix = "spring.kafka.consumer")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class KafkaConsumerProperties {
    private String bootstrapServers;
    private String groupId;
}
