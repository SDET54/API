package patch_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonplaceholderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Patch01 extends JsonplaceholderBaseUrl {
    /*
        Given
	        1) https://jsonplaceholder.typicode.com/todos/198
	        2) {
                 "title": "Wash the dishes"
               }
        When
	 		I send PATCH Request to the Url
	    Then
	   	   Status code is 200
	   	   And response body is like   {
									    "userId": 10,
									    "title": "Wash the dishes",
									    "completed": true,
									    "id": 198
									   }
     */
    @Test
    public void patch01() {
        //set the url
        specJsonplaceholder.pathParams("pp1", "todos", "pp2", "198");

        //set the request body
        JsonplaceholderTestData jTd = new JsonplaceholderTestData();
        Map<String, Object> requestBody = jTd.expectedDataWithMissingKeys(null, "Wash the dishes", null);

        //send the patch request, get the response
        Response response = given().spec(specJsonplaceholder).contentType(ContentType.JSON).body(requestBody).when().patch("{pp1}/{pp2}");
        //response.prettyPrint();

        Map<String, Object> actualBody = response.as(HashMap.class);

        //do assertions

        assertEquals(jTd.expectedPatchStatusCode, response.getStatusCode());
        assertEquals(requestBody.get("title"), actualBody.get("title"));
    }
}
