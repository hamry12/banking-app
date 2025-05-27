package com.example.notification.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "kafka.default")
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class KafkaDefaults {
    private int partitions;
    private int replicationFactor;
    private int minInSyncReplicas;
}