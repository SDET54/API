package put_requests;

import base_urls.DummyrestapiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.*;
import pojos.DummyrestapiPojos.*;
import utils.JsonUtil;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.*;

public class Put02 extends DummyrestapiBaseUrl {
    /*
        URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Tom Hanks",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/update/21
    And
        {
            "employee_name": "Tom Hanks",
            "employee_salary": 111111,
            "employee_age": 23,
            "profile_image": "Perfect image"
        }
    When
         user sends the put request
    Then
         Status code is 200
    And
        Response body should be like the following
         {
              "status": "success",
              "data": {
                  "employee_name": "Tom Hanks",
                  "employee_salary": 111111,
                  "employee_age": 23,
                  "profile_image": "Perfect image"
              },
              "message": "Successfully! Record has been updated."
         }

     */

    @Test
    public void put01() {
        specDummyrestapi.pathParams("pp1", "update", "pp2", 21);

        DummyDataPojo requestBody = new DummyDataPojo("Perfect image", "Tom Hanks", 111111, 23);
        DummyResponsePojo expectedBody = new DummyResponsePojo(requestBody, "Successfully! Record has been updated.", "success");

        Response response = given().spec(specDummyrestapi).contentType(ContentType.JSON).body(requestBody).when().put("{pp1}/{pp2}");

        DummyResponsePojo actualBody = JsonUtil.convertJsonToJavaObject(response.asString(), DummyResponsePojo.class);

        assertEquals(200, response.getStatusCode());
        assertEquals(expectedBody.getStatus(), actualBody.getStatus());
        assertEquals(expectedBody.getData().getProfile_image(), actualBody.getData().getProfile_image());
        assertEquals(expectedBody.getData().getEmployee_name(), actualBody.getData().getEmployee_name());
        assertEquals(expectedBody.getData().getEmployee_salary(), actualBody.getData().getEmployee_salary());
        assertEquals(expectedBody.getData().getEmployee_age(), actualBody.getData().getEmployee_age());
        assertEquals(expectedBody.getMessage(), actualBody.getMessage());
    }
}
