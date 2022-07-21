package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.JsonplaceholderPojo;
import testDataDeposu.JsonplaceholderTestData;
import utils.JsonUtil;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get14ObjectMapper extends JsonplaceholderBaseUrl {

    JsonplaceholderTestData jTd;

    /*
        Given
	        https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send GET Request to the URL
	 	Then
	 		Status code is 200
	 		And response body is like {
									    "userId": 10,
									    "id": 198,
									    "title": "quis eius est sint explicabo",
									    "completed": true
									  }
     */

    @Test
    public void get01ObjectMapper() {
        //set the url
        specJsonplaceholder.pathParams("pp1", "todos", "pp2", 198);

        //set the expected data
        jTd = new JsonplaceholderTestData();
        String expectedData = jTd.expectedData(10, "quis eius est sint explicabo", true);

        HashMap<String, Object> expectedDataMap = JsonUtil.convertJsonToJavaObject(expectedData, HashMap.class);

        //send the get request, get the response
        Response response = given().spec(specJsonplaceholder).when().get("{pp1}/{pp2}");

        HashMap<String, Object> actualDataMap = JsonUtil.convertJsonToJavaObject(response.asString(), HashMap.class);
        //do assertions
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
    }

    @Test
    public void get02ObjectMapper() {
        //set the url
        specJsonplaceholder.pathParams("pp1", "todos", "pp2", 198);

        //set the expected data
        jTd = new JsonplaceholderTestData();
        String expectedData = jTd.expectedData(10, "quis eius est sint explicabo", true);

        JsonplaceholderPojo expectedDataMap = JsonUtil.convertJsonToJavaObject(expectedData, JsonplaceholderPojo.class);

        //send the get request, get the response
        Response response = given().spec(specJsonplaceholder).when().get("{pp1}/{pp2}");

        JsonplaceholderPojo actualDataMap = JsonUtil.convertJsonToJavaObject(response.asString(), JsonplaceholderPojo.class);

        //do assertions
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedDataMap.getCompleted(), actualDataMap.getCompleted());
        assertEquals(expectedDataMap.getTitle(), actualDataMap.getTitle());
        assertEquals(expectedDataMap.getUserId(), actualDataMap.getUserId());
    }
}
