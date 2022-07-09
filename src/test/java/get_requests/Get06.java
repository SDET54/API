package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get06 extends HerokuappBaseUrl {
    /*
        Given
            https://restful-booker.herokuapp.com/booking/1268
        When
            User send a GET request to the URL
        Then
            HTTP Status Code should be 200
        And
            Response content type is “application/json”
        And
            Response body should be like;
          {
            "firstname": "Sally",
            "lastname": "Brown",
            "totalprice": 111,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2013-02-23",
                "checkout": "2014-10-23"
            },
            "additionalneeds": "Breakfast"
        }
     */
    @Test
    public void get01() {
        //set the url
        specHerokuapp.pathParams("pp1", "booking", "pp2", 1268);

        //send the request, get the response
        Response response = given().spec(specHerokuapp).when().get("{pp1}/{pp2}");
        //response.prettyPrint();

        //do assertions
        //1. Yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Sally"),
                        "lastname", equalTo("Brown"),
                        "totalprice", equalTo(111),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2013-02-23"),
                        "bookingdates.checkout", equalTo("2014-10-23"),
                        "additionalneeds", equalTo("Breakfast"));

        //2.Yol JsonPath Class
        JsonPath jsonPath = response.jsonPath();
        assertEquals("Sally", jsonPath.getString("firstname"));
        assertEquals("Brown", jsonPath.getString("lastname"));
        assertEquals(111, jsonPath.getInt("totalprice"));
        assertTrue(jsonPath.getBoolean("depositpaid"));
        assertEquals("2013-02-23", jsonPath.getString("bookingdates.checkin"));
        assertEquals("2014-10-23", jsonPath.getString("bookingdates.checkout"));
        assertEquals("Breakfast", jsonPath.getString("additionalneeds"));

        //3.Yol Soft Assertion

        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(jsonPath.getString("firstname"), "Sally", "firstname uyusmadi");
        softAssert.assertEquals(jsonPath.getString("lastname"), "Brown", "lastname uyusmadi");
        softAssert.assertEquals(jsonPath.getInt("totalprice"), 111, "totalprice uyusmadi");
        softAssert.assertTrue(jsonPath.getBoolean("depositpaid"), "depositpaid false");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkin"), "2013-02-23", "checkin uyusmadi");
        softAssert.assertEquals(jsonPath.getString("bookingdates.checkout"), "2014-10-23", "checkout uyusmadi");
        softAssert.assertEquals(jsonPath.getString("additionalneeds"), "Breakfast", "additionalneeds uyusmadi");

        softAssert.assertAll();


    }

}
