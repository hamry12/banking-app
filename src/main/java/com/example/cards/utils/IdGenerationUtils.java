package com.example.cards.utils;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class IdGenerationUtils {


    /**
     * Generates a unique account ID.
     *
     * <p>This method uses the {@link UUID#randomUUID()} method to generate a unique identifier.
     * The identifier is then converted to a long by removing all non-numeric characters and
     * taking the first 12 characters.
     *
     * @return the account ID.
     */
    public Long generateUUID() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 12);
        return Long.parseLong(uuid);
    }
}
