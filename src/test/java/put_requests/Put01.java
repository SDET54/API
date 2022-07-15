package put_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonplaceholderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonplaceholderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "userId": 21,
                 "title": "Wash the dishes",
                 "completed": false
               }
        When
	 		I send PUT Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 21,
									    "title": "Wash the dishes",
									    "completed": false
									   }
     */
    @Test
    public void post01() {
        //set the url
        specJsonplaceholder.pathParams("pp1", "todos", "pp2", "198");

        //set the expected data
        JsonplaceholderTestData jTd = new JsonplaceholderTestData();
        Map<String, Object> expectedData = jTd.expectedDataWithAllKeys(21, "Wash the dishes", false);

        //send the put request, get the response
        Response response = given().spec(specJsonplaceholder).contentType(ContentType.JSON).body(expectedData).when().put("{pp1}/{pp2}");
        //response.prettyPrint();

        //do assertions
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(jTd.expectedPutStatusCode, response.getStatusCode());
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
    }
}
