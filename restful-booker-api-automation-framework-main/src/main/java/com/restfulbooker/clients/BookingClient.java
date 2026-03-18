package com.restfulbooker.clients;

import com.restfulbooker.endpoints.APIEndpoints;
import com.restfulbooker.models.Booking;
import com.restfulbooker.utils.LoggerUtils;
import com.restfulbooker.utils.RequestUtils;
import io.restassured.response.Response;

public class BookingClient {

    public static Response getBooking(int id){
        LoggerUtils.info("Getting booking: " + id);
        String endpoint = APIEndpoints.BOOKING_BY_ID.replace("{id}", String.valueOf(id));
        return RequestUtils.get(endpoint);
    }

    public static Response createBooking(Booking booking){
        LoggerUtils.info("Creating booking for: " + booking.getFirstname());
        return RequestUtils.post(APIEndpoints.BOOKING,booking);

    }

    public static Response updateBooking(int id, Booking booking,String token){
        LoggerUtils.info("Updating booking: " + id);
        String endpoint = APIEndpoints.BOOKING_BY_ID.replace("{id}", String.valueOf(id));
        return RequestUtils.put(endpoint, booking, token);
    }

    public static Response partialUpdateBooking(int id, Booking booking,String token){
        LoggerUtils.info("Partially updating booking: " + id);
        String endpoint = APIEndpoints.BOOKING_BY_ID.replace("{id}", String.valueOf(id));
        return RequestUtils.patch(endpoint, booking, token);
    }

    public static Response deleteBooking(int id, String token) {
        LoggerUtils.info("Deleting booking: " + id);
        String endpoint = APIEndpoints.BOOKING_BY_ID.replace("{id}", String.valueOf(id));
        return RequestUtils.delete(endpoint,token);
    }
}
