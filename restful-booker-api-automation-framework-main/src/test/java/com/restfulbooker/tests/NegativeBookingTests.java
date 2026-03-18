package com.restfulbooker.tests;

import com.restfulbooker.base.BaseTest;
import com.restfulbooker.clients.BookingClient;
import com.restfulbooker.models.Booking;
import com.restfulbooker.models.BookingDates;
import com.restfulbooker.utils.TestDataBuilder;
import io.restassured.response.Response;
import io.qameta.allure.*;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeBookingTests extends BaseTest {

    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Story("Authentication")
    @Description("Test that invalid authentication token returns 403 Forbidden")
    public void testInvalidToken(){
        Response response = BookingClient.deleteBooking(1,"invalidtoken123");
        Assert.assertEquals(response.getStatusCode(),403);
    }

    @Test()
    @Severity(SeverityLevel.CRITICAL)
    @Story("Authentication")
    @Description("Test that empty authentication token returns 403 Forbidden")
    public void testUpdateWithoutToken() {
        BookingDates dates = BookingDates.builder()
                .checkin("2026-03-09")
                .checkout("2026-03-12")
                .build();

        Booking booking = Booking.builder()
                .firstname("UpdatedFirstName")
                .lastname("UpdatedLastName")
                .totalPrice(999)
                .depositPaid(false)
                .bookingDates(dates)
                .additionalNeeds("Lunch")
                .build();

        Response response = BookingClient.updateBooking(1, booking, "");
        Assert.assertEquals(response.getStatusCode(),403);
    }

    @Test()
    @Severity(SeverityLevel.MINOR)
    @Story("Booking Management")
    @Description("Test that retrieving non-existent booking returns 404 Not Found")
    public void testGetNonExistentBooking(){
        Response response = BookingClient.getBooking(999999999);
        Assert.assertEquals(response.getStatusCode(),404);
    }

    @Test()
    @Severity(SeverityLevel.NORMAL)
    @Story("Booking Management")
    @Description("Test that creating booking with invalid data returns server error 500")
    public void testCreateBookingMissingFields(){
       Booking booking = TestDataBuilder.getBooking("invalidBooking");
        Response response = BookingClient.createBooking(booking);
       Assert.assertEquals(response.getStatusCode(),500);
    }



}

