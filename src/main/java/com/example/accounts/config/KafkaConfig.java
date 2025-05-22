package com.example.accounts.config;

import com.example.events.NotificationEvent;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
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

    @Bean
    public ProducerFactory<String, NotificationEvent> producerFactory(){
        Map<String,Object> configProp= new HashMap<>();
        configProp.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configProp.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configProp.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return new org.springframework.kafka.core.DefaultKafkaProducerFactory<>(configProp);
    }

    @Bean
    public KafkaTemplate<String, NotificationEvent> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }
}