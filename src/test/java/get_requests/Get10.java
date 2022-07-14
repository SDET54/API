package get_requests;

import base_urls.GorestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import testDataDeposu.GorestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get10 extends GorestBaseUrl {
    /*
        Given
            https://gorest.co.in/public/v1/users/2971
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
           {
    "meta": null,
    "data": {
        "id": 2971,
        "name": "Dev Nambeesan",
        "email": "nambeesan_dev@mayert.net",
        "gender": "female",
        "status": "active"
    }
}
     */
    @Test
    public void get01() {
        //set the url
        specGorest.pathParams("pp1", "public", "pp2", "v1", "pp3", "users", "pp4", 2971);

        //set the expected data
        GorestTestData gTd = new GorestTestData();

        Map<String, String> dataMap = gTd.dataMap("Dev Nambeesan", "nambeesan_dev@mayert.net", "female", "active");

        Map<String, Object> expectedData = gTd.expectedData(null, dataMap);
        //System.out.println(expectedData);

        //send the request, get the response
        Response response = given().spec(specGorest).when().get("{pp1}/{pp2}/{pp3}/{pp4}");
        //response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);
        //System.out.println(actualData);

        //do assertions
        assertEquals(gTd.expectedStatusCode, response.getStatusCode());
        assertEquals(expectedData.get("meta"), actualData.get("meta"));
        assertEquals(((Map) expectedData.get("data")).get("name"), ((Map) actualData.get("data")).get("name"));
        assertEquals(((Map) expectedData.get("data")).get("email"), ((Map) actualData.get("data")).get("email"));
        assertEquals(((Map) expectedData.get("data")).get("gender"), ((Map) actualData.get("data")).get("gender"));
        assertEquals(((Map) expectedData.get("data")).get("status"), ((Map) actualData.get("data")).get("status"));

    }
}
