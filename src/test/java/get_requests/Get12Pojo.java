package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuappPojos.HerokuappBookingPojo;
import pojos.HerokuappPojos.HerokuappBookingdatesPojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends HerokuappBaseUrl {

    /*
    Given
            https://restful-booker.herokuapp.com/booking/76
        When
 		    I send GET Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
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
    public void getPojo01() {
        //set the url
        specHerokuapp.pathParams("pp1", "booking", "pp2", "76");

        //set the expectedBody
        HerokuappBookingdatesPojo bookingdates = new HerokuappBookingdatesPojo("2013-02-23", "2014-10-23");
        HerokuappBookingPojo expectedBody = new HerokuappBookingPojo("Sally", "Brown", 111, true, bookingdates, "Breakfast");

        //send the get request, get the response
        Response response = given().spec(specHerokuapp).when().get("{pp1}/{pp2}");

        HerokuappBookingPojo actualBody = response.as(HerokuappBookingPojo.class);

        //do assertions
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedBody.toString(), actualBody.toString());
    }

}
