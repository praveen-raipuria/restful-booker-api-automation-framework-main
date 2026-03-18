package com.restfulbooker.utils;

import com.restfulbooker.config.ConfigManager;

public final class FrameworkConstants {

    private FrameworkConstants() {
    }
    public static final int TIMEOUT = Integer.parseInt(ConfigManager.get("timeout"));
    public static final String ACCEPT = "Accept";
    public static final String APPLICATION_JSON = "application/json";
    public static final String BOOKINGS_JSON_PATH = "src/test/resources/testdata/booking.json";
    public static final String BOOKING_SCHEMA = "schemas/booking-schema.json";

}