package com.restfulbooker.utils;

import com.restfulbooker.specs.RequestFactory;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestUtils {

    public static Response get(String endpoint){
        LoggerUtils.info("GET → " + endpoint);
        return given()
                .spec(RequestFactory.getRequestSpec())
                .when()
                .get(endpoint);

    }


    public static Response post(String endpoint, Object body) {
        LoggerUtils.info("POST → " + endpoint);
        return given()
                .spec(RequestFactory.getRequestSpec())
                .body(body)
                .when()
                .post(endpoint);

    }

    public static Response put(String endpoint, Object body, String token) {
        LoggerUtils.info("PUT → " + endpoint);
        return given()
                .spec(RequestFactory.getRequestSpec())
                .cookie("token", token)
                .body(body)
                .when()
                .put(endpoint);
    }


    public static Response patch(String endpoint, Object body, String token) {
        LoggerUtils.info("PATCH → " + endpoint);
        return given()
                .spec(RequestFactory.getRequestSpec())
                .cookie("token", token)
                .body(body)
                .when()
                .patch(endpoint);
    }

    public static Response delete(String endpoint, String token) {
        LoggerUtils.info("DELETE → " + endpoint);
        return given()
                .spec(RequestFactory.getRequestSpec())
                .cookie("token", token)
                .when()
                .delete(endpoint);
    }
}
