package RestAssuredBasics.GET;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class APITesting006_NonBDDStyle_GET {

  static  RequestSpecification r = RestAssured.given();


   @Severity(SeverityLevel.CRITICAL)
   @Description("TC1 - NonBDDStyle_GET - Positive.given")
   @Test
    public void Test_NonBBDStyle_GET () {

        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/110092");
        r.when().log().all().get();
        r.then().log().all().statusCode(200);

    }


    @Severity(SeverityLevel.NORMAL)
    @Description("TC2 - NonBDDStyle_GET - Negative.given")
    @Test
    public void Test_NonBBDStyle_GET_Negative() {

        r.baseUri("https://api.zippopotam.us");
        r.basePath("/IN/-1");
        r.when().log().all().get();
        r.then().log().all().statusCode(404);


    }
}
