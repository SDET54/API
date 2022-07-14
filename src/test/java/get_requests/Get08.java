package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.JsonplaceholderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

public class Get08 extends JsonplaceholderBaseUrl {
    /*
         Given
            https://jsonplaceholder.typicode.com/todos/2
        When
            I send GET Request to the URL
        Then
            Status code is 200
            And "completed" is false
            And "userId" is 1
            And "title" is "quis ut nam facilis et officia qui"
            And header "Via" is "1.1 vegur"
            And header "Server" is "cloudflare"
            {
                "userId": 1,
                "id": 2,
                "title": "quis ut nam facilis et officia qui",
                "completed": false
            }
     */

    /*
    JSON formatindan JAVA objesine convert = de-serialization
    JAVA objesini JSON formatina convert = serialization
       De-Serialization ve Serialization iki turlu yapilir:
          Gson: Google tarafindan uretilmistir.
          Object Mapper: Daha populer ***
    */


    @Test
    public void get01() {

        //set the url
        specJsonplaceholder.pathParams("pp1", "todos", "pp2", 2);

        //set the expected data
        Map<String, Object> expectedData = new HashMap<>();

        expectedData.put("userId", 1);
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("completed", false);
        expectedData.put("StatusCode", 200);
        expectedData.put("headerVia", "1.1 vegur");
        expectedData.put("headerServer", "cloudflare");


        //send the request, get the response
        Response response = given().spec(specJsonplaceholder).when().get("{pp1}/{pp2}");

        Map<String, Object> actualData = response.as(HashMap.class);

        //do assertions
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("headerVia"), response.getHeader("Via"));
        assertEquals(expectedData.get("headerServer"), response.getHeader("Server"));


    }

    @Test
    public void get02() {
        //set the url
        specJsonplaceholder.pathParams("pp1", "todos", "pp2", 2);

        //set the expected data
        JsonplaceholderTestData jTd = new JsonplaceholderTestData();
        Map<String, Object> expectedData = jTd.expectedDataWithAllKeys(1, "quis ut nam facilis et officia qui", false);
        expectedData.put("StatusCode", 200);
        expectedData.put("headerVia", "1.1 vegur");
        expectedData.put("headerServer", "cloudflare");

        //send the request, get the response
        Response response = given().spec(specJsonplaceholder).when().get("{pp1}/{pp2}");

        Map<String, Object> actualData = response.as(HashMap.class);

        //do assertions
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("StatusCode"), response.getStatusCode());
        assertEquals(expectedData.get("headerVia"), response.getHeader("Via"));
        assertEquals(expectedData.get("headerServer"), response.getHeader("Server"));


    }
}
