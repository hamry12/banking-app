package com.example.accounts.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.Map;

@Configuration
public class KafkaConfig {

    @Bean
    public KafkaAdmin.NewTopics createTopic(KafkaTopics kafkaTopics, KafkaDefaults kafkaDefaults){
        return new KafkaAdmin.NewTopics(
                kafkaTopics.getTopics().entrySet().stream()
                        .map(entry -> TopicBuilder.name(entry.getValue())
                                .partitions(kafkaDefaults.getPartitions())
                                .replicas(kafkaDefaults.getReplicationFactor())
                                .config("min.insync.replicas", String.valueOf(kafkaDefaults.getMinInSyncReplicas()))
                                .build())
                        .toArray(NewTopic[]::new)
        );
    }
}
