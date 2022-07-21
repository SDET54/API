package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import pojos.HerokuappPojos.HerokuappBookingPojo;
import pojos.HerokuappPojos.HerokuappResponsePojo;
import testDataDeposu.HerokuappTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15ObjectMapper extends HerokuappBaseUrl {
    /*
    Given
	            https://restful-booker.herokuapp.com/booking/22
        When
		 		I send GET Request to the URL
		Then
		 		Status code is 200
                {
                     "firstname": "Oliver",
                     "lastname": "Smith",
                     "totalprice": 100,
                     "depositpaid": true,
                     "bookingdates": {
                         "checkin": "2022-07-18",
                         "checkout": "2022-07-19"
                     },
                     "additionalneeds": "Breakfast"
                 }
     */

    @Test
    public void get01ObjectMapper() {
        //set the url
        specHerokuapp.pathParams("pp1", "booking", "pp2", 22);

        //set the expected data
        HerokuappTestData hTd = new HerokuappTestData();
        String expectedStr = hTd.expectedStr("Oliver", "Smith", 100, true, "2022-07-18", "2022-07-19", "Breakfast");

        HerokuappBookingPojo expectedData = JsonUtil.convertJsonToJavaObject(expectedStr, HerokuappBookingPojo.class);

        //send the get request, get the response
        Response response = given().spec(specHerokuapp).when().get("{pp1}/{pp2}");

        HerokuappBookingPojo actualData = JsonUtil.convertJsonToJavaObject(response.asString(), HerokuappBookingPojo.class);

        //do assertions
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.toString(), actualData.toString());

        //Soft Assertion
        //1.step: Create SoftAssert object
        SoftAssert softAssert = new SoftAssert();

        //2.step: do assertion
        softAssert.assertEquals(actualData.getFirstname(), expectedData.getFirstname(), "firstname uyusmadi");
        softAssert.assertEquals(actualData.getLastname(), expectedData.getLastname(), "lastname uyusmadi");
        softAssert.assertEquals(actualData.getTotalprice(), expectedData.getTotalprice(), "totalprice uyusmadi");
        softAssert.assertEquals(actualData.getDepositpaid(), expectedData.getDepositpaid(), "depositpaid uyusmadi");
        softAssert.assertEquals(actualData.getBookingdates().getCheckin(), expectedData.getBookingdates().getCheckin(), "checkin uyusmadi");
        softAssert.assertEquals(actualData.getBookingdates().getCheckout(), expectedData.getBookingdates().getCheckout(), "checkout uyusmadi");
        softAssert.assertEquals(actualData.getAdditionalneeds(), expectedData.getAdditionalneeds(), "additionalneeds uyusmadi");

        //3.step: assertAll() methodunu calistir
        softAssert.assertAll();
    }
}
