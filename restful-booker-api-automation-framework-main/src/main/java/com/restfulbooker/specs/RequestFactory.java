package com.restfulbooker.specs;

import com.restfulbooker.config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import com.restfulbooker.utils.FrameworkConstants;

public class RequestFactory {

    private RequestFactory() {}

    public static RequestSpecification getRequestSpec() {

        int timeout = Integer.parseInt(ConfigManager.get("timeout"));

        RestAssuredConfig config = RestAssuredConfig.config()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", timeout)
                        .setParam("http.socket.timeout",     timeout));

        return new RequestSpecBuilder()
                .setBaseUri(ConfigManager.get("base.url"))
                .setContentType(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .addHeader(FrameworkConstants.ACCEPT, FrameworkConstants.APPLICATION_JSON)
                .setConfig(config)
                .log(LogDetail.ALL)
                .build();
    }
}