package com.example.accounts.utils;

import com.example.accounts.dto.AddressRequestDto;
import com.example.accounts.dto.CustomerRegistrationDto;
import com.example.accounts.entity.Address;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@Component
public class IdGenerationUtils {

    /**
     * Generates a unique customer ID based on customer registration details.
     *
     * <p>The method creates a customer ID by concatenating the first two letters of the
     * country, state, and city from the customer's permanent address, followed by a
     * generated timestamp.
     *
     * @param customerRegistrationDto the DTO containing customer registration details
     * @return the generated customer ID
     */

    public String generateCustomerId(CustomerRegistrationDto customerRegistrationDto) {
        String timestamp=String.valueOf(generateTimestamp());
        List<AddressRequestDto> addressList= customerRegistrationDto.getAddresses();
        AddressRequestDto address = addressList
                .stream()
                .filter(list -> list.getAddressType() == 1)
                .findFirst().orElse(new AddressRequestDto());

        String country=address.getCountry().toLowerCase(Locale.ROOT);
        String state=address.getState().toLowerCase();
        String city=address.getCity().toLowerCase();
        return country.substring(0,2)
                .concat(state.substring(0,2))
                .concat(city.substring(0,2))
                .concat(timestamp);
    }

    /**
     * Generates a timestamp.
     *
     * <p>This method uses the system's current time in milliseconds to generate a unique
     * identifier. The identifier is then converted to an int by casting the long value to an
     * int.
     *
     * @return the timestamp.
     */
    public static int generateTimestamp() {
        long timestamp=System.currentTimeMillis();
        return (int)timestamp;
    }

    /**
     * Generates a unique account ID.
     *
     * <p>This method uses the {@link UUID#randomUUID()} method to generate a unique identifier.
     * The identifier is then converted to a long by removing all non-numeric characters and
     * taking the first 12 characters.
     *
     * @return the account ID.
     */
    public Long generateAccountId() {
        String uuid = UUID.randomUUID().toString().replaceAll("[^0-9]", "").substring(0, 12);
        return Long.parseLong(uuid);
    }
}
