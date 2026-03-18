package com.restfulbooker.tests;

import com.restfulbooker.clients.BookingClient;
import com.restfulbooker.models.Booking;
import com.restfulbooker.models.BookingDates;
import com.restfulbooker.base.BaseTest;
import com.restfulbooker.utils.FrameworkConstants;
import com.restfulbooker.utils.LoggerUtils;
import com.restfulbooker.utils.RandomDataUtil;
import io.restassured.response.Response;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class BookingE2ETests extends BaseTest {

    private int bookingId;

    @Test(description = "Create a new booking")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Booking Management")
    @Description("Test creating a new booking with valid data")
    public void testCreateBooking() {
        BookingDates dates = BookingDates.builder()
                .checkin(RandomDataUtil.getRandomCheckinDate())
                .checkout(RandomDataUtil.getRandomCheckoutDate())
                .build();

        Booking booking = Booking.builder()
                .firstname(RandomDataUtil.getRandomFirstName())
                .lastname(RandomDataUtil.getRandomLastName())
                .totalPrice(RandomDataUtil.getRandomPrice())
                .depositPaid(RandomDataUtil.getRandomDepositPaidStatus())
                .bookingDates(dates)
                .additionalNeeds(RandomDataUtil.getRandomAdditionalNeeds())
                .build();

        Response response = BookingClient.createBooking(booking);

        Assert.assertEquals(response.getStatusCode(), 200);
        // POST returns data wrapped in "booking" object
        Assert.assertEquals(response.jsonPath().getString("booking.firstname"), booking.getFirstname());
        Assert.assertEquals(response.jsonPath().getString("booking.lastname"), booking.getLastname());

        bookingId = response.jsonPath().getInt("bookingid");
        LoggerUtils.info("Booking created with ID: " + bookingId);
    }

    @Test(dependsOnMethods = "testCreateBooking")
    @Severity(SeverityLevel.NORMAL)
    @Story("Booking Management")
    @Description("Get the created booking")
    public void testGetBooking() {
        Response response = BookingClient.getBooking(bookingId);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNotNull(response.jsonPath().getString("firstname"));
        Assert.assertNotNull(response.jsonPath().getString("lastname"));
        response.then().assertThat()
                .body(matchesJsonSchemaInClasspath(FrameworkConstants.BOOKING_SCHEMA));
    }

    @Test(dependsOnMethods = "testGetBooking")
    @Severity(SeverityLevel.NORMAL)
    @Story("Booking Management")
    @Description("Test updating all booking fields")
    public void testUpdateBooking() {

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

        Response response = BookingClient.updateBooking(bookingId, booking, token);

        Assert.assertEquals(response.getStatusCode(), 200);
        // PUT returns data directly (no wrapper)
        Assert.assertEquals(response.jsonPath().getString("firstname"), booking.getFirstname());
        Assert.assertEquals(response.jsonPath().getInt("totalprice"), 999);

    }

    @Test(dependsOnMethods = "testUpdateBooking")
    @Severity(SeverityLevel.MINOR)
    @Story("Booking Management")
    @Description("Test updating specific booking fields")
    public void testPartialUpdateBooking() {

        Booking booking = Booking.builder()
                .firstname("PatchedName")
                .lastname("PatchedLastName")
                .build();

        Response response = BookingClient.partialUpdateBooking(bookingId, booking, token);

        Assert.assertEquals(response.getStatusCode(), 200);
        // PATCH returns data directly (no wrapper)
        Assert.assertEquals(response.jsonPath().getString("firstname"), "PatchedName");
        Assert.assertEquals(response.jsonPath().getString("lastname"), "PatchedLastName");
    }

    @Test(dependsOnMethods = "testPartialUpdateBooking")
    @Severity(SeverityLevel.CRITICAL)
    @Story("Booking Management")
    @Description("Test deleting a booking")
    public void testDeleteBooking() {
        Response response = BookingClient.deleteBooking(bookingId, token);
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Test(dependsOnMethods = "testDeleteBooking")
    @Severity(SeverityLevel.NORMAL)
    @Story("Booking Management")
    @Description("Test that deleted booking returns 404")
    public void testVerifyDeletion() {
        Response response = BookingClient.getBooking(bookingId);
        Assert.assertEquals(response.getStatusCode(), 404);
    }


}
