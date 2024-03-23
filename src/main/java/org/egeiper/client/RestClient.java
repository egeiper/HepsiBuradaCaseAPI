package org.egeiper.client;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import io.restassured.internal.RequestSpecificationImpl;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class RestClient {

    String baseURL;

    public RestClient(String baseURL){
        this.baseURL = baseURL;
    }


    public void attachment(RequestSpecification httpRequest, String baseUrl, Response response) {

        String html = "Url = " + baseUrl + "\n \n" +
                "Request Headers = " + ((RequestSpecificationImpl) httpRequest).getHeaders() + "\n \n" +
                "Request Body = " + ((RequestSpecificationImpl) httpRequest).getBody() + "\n \n" +
                "Response Body = " + response.getBody().prettyPrint();
        Allure.addAttachment("Request-Response Detail", html);
    }

    public Response get(String path, Map<String, Object> params, Map<String, Object> headers, Object body) {
        RequestSpecification httpRequest = RestAssured.given()
                .log()
                .all(true);
        httpRequest.baseUri(baseURL);
        httpRequest.header("Content-Type", "application/json");
        if (params != null) {
            httpRequest.params(params);
        }
        if (headers != null) {
            httpRequest.headers(headers);
        }
        if (body != null) {
            httpRequest.body(body);
        }

        Response response = httpRequest.get(path);
        response.then().log().body();
        attachment(httpRequest, path, response);
        return response;
    }

    public Response post(String path, Map<String, Object> params, Map<String, Object> headers, Object body) {
        RequestSpecification httpRequest = RestAssured.given()
                .log()
                .all(true);
        httpRequest.baseUri(baseURL);
        httpRequest.header("Content-Type", "application/json");
        if (params != null) {
            httpRequest.queryParams(params);
        }
        if (headers != null) {
            httpRequest.headers(headers);
        }
        if (body != null) {
            httpRequest.body(body);
        }

        Response response = httpRequest.post(path);
        response.then().log().body();
        attachment(httpRequest, path, response);
        return response;
    }

    public Response delete(String path, Map<String, Object> params, Map<String, Object> headers, Object body) {
        RequestSpecification httpRequest = RestAssured.given()
                .log()
                .all(true);
        httpRequest.baseUri(baseURL);
        httpRequest.header("Content-Type", "application/json");
        if (params != null) {
            httpRequest.params(params);
        }
        if (headers != null) {
            httpRequest.headers(headers);
        }
        if (body != null) {
            httpRequest.body(body);
        }
        Response response = httpRequest.delete(path);
        response.then().log().body();
        attachment(httpRequest, path, response);
        return response;
    }
}
