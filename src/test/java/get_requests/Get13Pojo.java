package get_requests;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.GorestPojos.GorestDataPojo;
import pojos.GorestPojos.GorestResponsePojo;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GorestBaseUrl {
    /*
    Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
            {
                 "meta": null,
                 "data": {
                     "id": 2508,
                     "name": "Akshita Nehru",
                     "email": "nehru_akshita@jast.info",
                     "gender": "female",
                     "status": "active"
                 }
             }
     */

    @Test
    public void getPojo01() {
        //set the url
        specGorest.pathParams("pp1", "public", "pp2", "v1", "pp3", "users", "pp4", "2508");

        //set the expectedBody
        GorestDataPojo data = new GorestDataPojo(2508, "Akshita Nehru", "nehru_akshita@jast.info", "female", "active");
        GorestResponsePojo expectedBody = new GorestResponsePojo(null, data);

        //send the get request, get the response
        Response response = given().spec(specGorest).when().get("{pp1}/{pp2}/{pp3}/{pp4}");

        GorestResponsePojo actualBody = response.as(GorestResponsePojo.class);

        //do assertions
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedBody.getData().toString(), actualBody.getData().toString());
        assertEquals(expectedBody.getMeta(), actualBody.getMeta());
    }
}
