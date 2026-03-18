package com.restfulbooker.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restfulbooker.models.Booking;
import java.io.File;


public class TestDataBuilder {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static Booking getBooking(String key){

        try {
            JsonNode rootNode = mapper.readTree(new File(FrameworkConstants.BOOKINGS_JSON_PATH));
            return mapper.treeToValue(rootNode.get(key), Booking.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to read test data for key: " + key, e);
        }
    }
}
