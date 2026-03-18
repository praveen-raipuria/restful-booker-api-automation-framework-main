package com.restfulbooker.base;

import com.restfulbooker.clients.AuthClient;
import com.restfulbooker.endpoints.APIEndpoints;
import com.restfulbooker.utils.LoggerUtils;
import com.restfulbooker.utils.RequestUtils;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;


public class BaseTest {

    protected static String token;

    @BeforeSuite
    public void setup(){
        LoggerUtils.info("====== TEST SUITE STARTED ======");

        Response response = RequestUtils.get(APIEndpoints.PING);
        Assert.assertEquals(response.getStatusCode(),201);
        LoggerUtils.info("Health check passed — API is up");

        RestAssured.filters(new AllureRestAssured());

        token = AuthClient.generateToken();
        if (token == null || token.isEmpty()) {
            LoggerUtils.error("Could not generate Auth Token. Aborting suite...");
            throw new RuntimeException("Authentication failed during @BeforeSuite");
        }
        LoggerUtils.info("Setup complete. Token : " + token);
    }

    @AfterSuite
    public void teardown() {
        LoggerUtils.info("====== TEST SUITE COMPLETED ======");
    }

}
