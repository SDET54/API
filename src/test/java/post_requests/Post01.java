package post_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonplaceholderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonplaceholderBaseUrl {

    /*
         Given
           1) https://jsonplaceholder.typicode.com/todos
           2)  {
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
        JsonplaceholderTestData jTd = new JsonplaceholderTestData();
        Map<String, Object> expectedData = jTd.expectedDataWithAllKeys(55, "Tidy your room", false);

        //send post request, get the response

        Response response = given().spec(specJsonplaceholder).contentType(ContentType.JSON).body(expectedData).when().post("{pp1}");
        //response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);

        //do assertions
        response.then().assertThat().statusCode(201);

        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}
