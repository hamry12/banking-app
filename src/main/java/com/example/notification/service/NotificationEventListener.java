package com.example.notification.service;

import com.example.events.NotificationEvent;
import com.example.notification.exception.MessageProcessingException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class NotificationEventListener {

    private static final Logger log= LoggerFactory.getLogger(NotificationEventListener.class);
    private ObjectMapper objectMapper;

    @KafkaListener(
            topics = "account-created-topic",
            containerFactory = "listenerContainerFactory"
    )
    public void handleAccountCreatedEvent(NotificationEvent event) {
        System.out.println("Received event: " + event.getChannelList());
    }

}
