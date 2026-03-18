package com.restfulbooker.clients;

import com.restfulbooker.config.ConfigManager;
import com.restfulbooker.endpoints.APIEndpoints;
import com.restfulbooker.models.AuthRequest;
import com.restfulbooker.utils.LoggerUtils;
import com.restfulbooker.utils.RequestUtils;
import io.restassured.response.Response;

public class AuthClient {

    public static String generateToken() {


        LoggerUtils.info("Generating auth token...");

        AuthRequest authRequest = AuthRequest.builder()
                .username(ConfigManager.get("username"))
                .password(ConfigManager.get("password"))
                .build();

        Response response = RequestUtils.post(APIEndpoints.AUTH, authRequest);

        String token = response.jsonPath().getString("token");
        LoggerUtils.info("Token generated: " + token);
        return token;
    }
}
