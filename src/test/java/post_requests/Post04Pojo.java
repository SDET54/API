package post_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.HerokuappPojos.HerokuappBookingPojo;
import pojos.HerokuappPojos.HerokuappBookingdatesPojo;
import pojos.HerokuappPojos.HerokuappResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post04Pojo extends HerokuappBaseUrl {
    /*
         Given
            https://restful-booker.herokuapp.com/booking
            {
                "firstname": "Ali",
                "lastname": "Can",
                "totalprice": 999,
                "depositpaid": true,
                "bookingdates": {
                    "checkin": "2021-09-21",
                    "checkout": "2021-12-21"
                 }
                 "additionalneeds": "Breakfast with white tea, Dragon juice"
             }
        When
 		    I send POST Request to the URL
 	    Then
 		    Status code is 200
 		And
 		    Response body is like {
 		                            "bookingid": 16,
 		                            "booking" :{
                                        "firstname": "Ali",
                                        "lastname": "Can",
                                        "totalprice": 999,
                                        "depositpaid": true,
                                        "bookingdates": {
                                            "checkin": "2021-09-21",
                                            "checkout": "2021-12-21"
                                        }
                                        "additionalneeds": "Breakfast with white tea, Dragon juice"
                                     }
                                  }
     */

    @Test
    public void post01() {
        //set the url
        specHerokuapp.pathParam("pp1", "booking");

        //set the request body
        HerokuappBookingdatesPojo bookingdates = new HerokuappBookingdatesPojo("2021-09-21", "2021-12-21");

        HerokuappBookingPojo requestBody = new HerokuappBookingPojo("Ali", "Can", 999, true, bookingdates, "Breakfast with white tea, Dragon juice");

        //send the post request, get the response
        Response response = given().spec(specHerokuapp).contentType(ContentType.JSON).body(requestBody).when().post("{pp1}");
        //response.prettyPrint();

        HerokuappResponsePojo actualBody = response.as(HerokuappResponsePojo.class);

        //do assertions
        assertEquals(200, response.getStatusCode());

        //1.way
        assertEquals(requestBody.toString(), actualBody.getBooking().toString());

        //2.way
        assertEquals(requestBody.getFirstname(), actualBody.getBooking().getFirstname());
        assertEquals(requestBody.getLastname(), actualBody.getBooking().getLastname());
        assertEquals(requestBody.getTotalprice(), actualBody.getBooking().getTotalprice());
        assertEquals(requestBody.getDepositpaid(), actualBody.getBooking().getDepositpaid());
        assertEquals(requestBody.getBookingdates().getCheckin(), actualBody.getBooking().getBookingdates().getCheckin());
        assertEquals(requestBody.getBookingdates().getCheckout(), actualBody.getBooking().getBookingdates().getCheckout());
        assertEquals(requestBody.getAdditionalneeds(), actualBody.getBooking().getAdditionalneeds());

    }
}
