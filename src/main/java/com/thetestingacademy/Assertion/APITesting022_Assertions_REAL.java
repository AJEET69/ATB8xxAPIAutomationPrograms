package com.thetestingacademy.Assertion;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class APITesting022_Assertions_REAL {

    @Test
    public void test_post() {
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

        Response response = RestAssured.given()
                .baseUri("https://restful-booker.herokuapp.com/")
                .basePath("/booking")
                .contentType(ContentType.JSON)
                .body(payload_POST)
                .log().all()
                .when().post();

        // Hamcrest Assertions
        response.then().log().all()
                .statusCode(200)
                .body("booking.firstname", Matchers.equalTo("Pramod"))
                .body("booking.lastname", Matchers.equalTo("Dutta"))
                .body("booking.depositpaid", Matchers.equalTo(false))
                .body("booking", Matchers.notNullValue());

        // Extract data for other assertions
        Integer bookingId = response.jsonPath().getInt("bookingid");
        String firstname = response.jsonPath().getString("booking.firstname");
        String lastName = response.jsonPath().getString("booking.lastname");

        // TestNG Assertions
        Assert.assertNotNull(bookingId);
        Assert.assertEquals(firstname, "Pramod");
        Assert.assertEquals(lastName, "Dutta");

        // AssertJ Assertions
        assertThat(bookingId).isNotNull().isPositive();
        assertThat(firstname).isEqualTo("Pramod").isNotNull().isNotBlank();
    }
}