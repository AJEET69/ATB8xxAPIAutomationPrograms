package com.thetestingacademy.Integration;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class APITesting012_TestCase_Integration {

    // Create a Token
    // Create a Booking
    // Perform a PUT request
    // Verify that PUT is success by GET Request
    // Delete the ID
    // Verify it doesn't exist GET Request

    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    String bookingId;

    public  String getToken(){

        String payload = "{\n" +
                "         \"username\" : \"admin\",\n" +
                "          \"password\" : \"password123\"\n" +
                "          }";

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payload);

       // when - Response
        response = requestSpecification.when().post();

      // then - Validatable Response
        ValidatableResponse validatableResponse = response.then();
        validatableResponse.statusCode(200);

        // Extract the token
        token = response.jsonPath().getString("token");
        System.out.println(token);

        assertThat(token).isAlphanumeric().isNotEmpty().isNotNull().isNotBlank();


        return token;
    }


    public String getBookingId() {
       String payload_POST = "{\n" +
                "    \"firstname\" : \"Pramod\",\n" +
                "    \"lastname\" : \"Dutta\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";

       requestSpecification = RestAssured.given();
       requestSpecification.baseUri("https://restful-booker.herokuapp.com");
       requestSpecification.basePath("/booking");
       requestSpecification.contentType(ContentType.JSON);
       requestSpecification.body(payload_POST).log().all();

       response = requestSpecification.when().post();

       // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingId = response.jsonPath().getString("bookingid");
        System.out.println(bookingId);


        assertThat(bookingId).isNotEmpty().isNotNull().isNotBlank();


        return bookingId;

    }

    @Test(priority = 1)
    public void Test_update_request_put(){
        token = getToken();
        bookingId = getBookingId();
        System.out.println(token);
        System.out.println(bookingId);


        String payload_PUT = "{\n" +
                "    \"firstname\" : \"Ajeet\",\n" +
                "    \"lastname\" : \"ji\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : false,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Lunch\"\n" +
                "}";



        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        requestSpecification.body(payload_PUT).log().all();

        // when - Response
        response = requestSpecification.when().put();

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);


    }



    @Test(priority = 2)
    public  void  Test_Update_request_Get(){
        System.out.println(bookingId);


        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingId);
        response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(200);

        String firstName = response.jsonPath().getString("firstname");
        Assert.assertEquals(firstName,"Ajeet");

    }

    @Test(priority = 3)
    public void Test_Delete_booking(){
        System.out.println(bookingId);
        System.out.println(token);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/"+bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);


        response = requestSpecification.when().delete();

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201); // #TODO #1 -Delete bug



    }

    @Test(priority = 4)
    public void Test_After_Delete_request_Get() {


        requestSpecification.baseUri("https://restful-booker.herokuapp.com");
        requestSpecification.basePath("/booking/" + bookingId);
        response = requestSpecification.when().log().all().get();
        requestSpecification.then().log().all().statusCode(404);

    }

    // Todo #2 I will add more assertions here

}
