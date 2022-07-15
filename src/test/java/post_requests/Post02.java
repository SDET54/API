package post_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.HerokuappTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends HerokuappBaseUrl {
    /*
    Given
            1) https://restful-booker.herokuapp.com/booking
            2) {
                 "firstname": "LaBebe",
                 "lastname": "Angara",
                 "totalprice": 11111,
                 "depositpaid": true,
                 "bookingdates": {
                     "checkin": "2021-09-09",
                     "checkout": "2021-09-21"
                  }
               }
        When
            I send POST Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "booking": {
                                                    "firstname": "LaBebe",
                                                    "lastname": "Angara",
                                                    "totalprice": 11111,
                                                    "depositpaid": true,
                                                    "bookingdates": {
                                                        "checkin": "2021-09-09",
                                                        "checkout": "2021-09-21"
                                                    }
                                                }
                                             }
     */
    @Test
    public void post01() {
        //set the url
        specHerokuapp.pathParam("pp1", "booking");

        //set the expected data
        HerokuappTestData hTd = new HerokuappTestData();

        Map<String, String> bookingDates = hTd.bookingdates("2021-09-09", "2021-09-21");

        Map<String, Object> expectedData = hTd.expectedData("LaBebe", "Angara", 11111, true, bookingDates);

        //send the post request, get the response
        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON).body(expectedData).when().post("{pp1}");
        //response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);

        //do assertions
        assertEquals(hTd.expectedStatusCode, response.getStatusCode());
        assertEquals(expectedData.get("firstname"), ((Map) actualData.get("booking")).get("firstname"));
        assertEquals(expectedData.get("lastname"), ((Map) actualData.get("booking")).get("lastname"));
        assertEquals(expectedData.get("totalprice"), ((Map) actualData.get("booking")).get("totalprice"));
        assertEquals(expectedData.get("depositpaid"), ((Map) actualData.get("booking")).get("depositpaid"));
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkin"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
        assertEquals(((Map) expectedData.get("bookingdates")).get("checkout"), ((Map) ((Map) actualData.get("booking")).get("bookingdates")).get("checkout"));


    }

}
