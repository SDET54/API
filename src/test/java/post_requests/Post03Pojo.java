package post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonplaceholderPojo;
import testDataDeposu.JsonplaceholderTestData;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post03Pojo extends JsonplaceholderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos
            {
            "userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
        When
            I send POST Request to the Url
        Then
            Status code is 201
        And
            response body is like {
                                    "userId": 55,
                                    "title": "Tidy your room",
                                    "completed": false,
                                    "id": 201
                                    }
     */
    @Test
    public void post01() {
        //set the url
        specJsonplaceholder.pathParam("pp1", "todos");

        //set the expected data
        JsonplaceholderPojo requestBody = new JsonplaceholderPojo(55, "Tidy your room", false);

        //set the post request, get the response
        Response response = given().spec(specJsonplaceholder).contentType(ContentType.JSON).body(requestBody).when().post("{pp1}");

        JsonplaceholderPojo actualBody = response.as(JsonplaceholderPojo.class);

        //do assertions
        assertEquals(requestBody.toString(), actualBody.toString());
    }
}
