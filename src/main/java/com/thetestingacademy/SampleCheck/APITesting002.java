package com.thetestingacademy.SampleCheck;

import io.restassured.RestAssured;

public class APITesting002 {
    public static void main(String[] args) {

        // Gherkins Syntax

        // full URL -> https://restful-booker.herokuapp.com/booking/1
        // base URI ->  https://restful-booker.herokuapp.com
        // base Path -> /booking/1

RestAssured
        .given()
              .baseUri("https://restful-booker.herokuapp.com")
              .basePath("/booking/1")
        .when()
            .get()
        .then().log().all()
             .statusCode(200);


   // Expected Status code <201> but was 200
    }
}
