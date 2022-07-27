package delete_request;

import base_urls.DummyrestapiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyrestapiPojos.DummyDeletePojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete02 extends DummyrestapiBaseUrl {
    /*
    URL: https://dummy.restapiexample.com/api/v1/delete/2
   HTTP Request Method: DELETE Request
   Test Case: Type by using Gherkin Language
   Assert:     i) Status code is 200
           ii) "status" is "success"
          iii) "data" is "2"
           iv) "message" is "Successfully! Record has been deleted"
 */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/delete/2
    When
        user sends the delete request
    Then
        Status code is 200
    And
        "status" is "success"
    And
        "data" is "2"
    And
        "message" is "Successfully! Record has been deleted"

     */
    @Test
    public void delete01() {
        specDummyrestapi.pathParams("pp1", "delete", "pp2", 2);

        DummyDeletePojo expectedBody = new DummyDeletePojo("success", "2", "Successfully! Record has been deleted");

        Response response = given().spec(specDummyrestapi).contentType(ContentType.JSON).when().delete("{pp1}/{pp2}");

        DummyDeletePojo actualBody = JsonUtil.convertJsonToJavaObject(response.asString(), DummyDeletePojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedBody.getMessage(), actualBody.getMessage());
        assertEquals(expectedBody.getData(), actualBody.getData());
        assertEquals(expectedBody.getStatus(), actualBody.getStatus());

    }
}
