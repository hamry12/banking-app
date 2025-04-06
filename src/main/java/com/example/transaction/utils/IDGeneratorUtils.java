package com.example.transaction.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class IDGeneratorUtils {

    /**
     * Generate a random unique identifier.
     * @return a unique identifier as a string
     */
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
