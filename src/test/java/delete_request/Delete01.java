package delete_request;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;


public class Delete01 extends JsonplaceholderBaseUrl {
    /*
           https://jsonplaceholder.typicode.com/todos/198
        When
	 		I send DELETE Request to the Url
	 	Then
		 	Status code is 200
		 	And Response body is { }
     */
    @Test
    public void delete01() {
        //set the url
        specJsonplaceholder.pathParams("pp1", "todos", "pp2", "198");

        //set the expected data

        // send the delete request, get the response
        Response response = given().spec(specJsonplaceholder).when().delete("{pp1}/{pp2}");
        //response.prettyPrint();

        //do assertions
        Map<String, Object> actualData = response.as(HashMap.class);

        assertEquals(200, response.getStatusCode());
        assertTrue(actualData.isEmpty());

    }
}
