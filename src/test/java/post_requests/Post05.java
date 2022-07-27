package post_requests;

import base_urls.DummyrestapiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyrestapiPojos.DummyDataPojo;
import pojos.DummyrestapiPojos.DummyResponsePojo;
import utils.JsonUtil;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post05 extends DummyrestapiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/create
       HTTP Request Method: POST Request
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
                            "profile_image": "Perfect image",
                            "id": 6344
                        },
                        "message": "Successfully! Record has been added."
                    }
     */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/create
        {
         "employee_name": "Tom Hanks",
         "employee_salary": 111111,
         "employee_age": 23,
         "profile_image": "Perfect image"
         }
    When
        user sends the post request
    Then
        Status code is 200
    And
        {
             "status": "success",
             "data": {
                 "employee_name": "Tom Hanks",
                 "employee_salary": 111111,
                 "employee_age": 23,
                 "profile_image": "Perfect image",
                 "id": 6344
             },
             "message": "Successfully! Record has been added."
         }

     */

    @Test
    public void post01() {
        //set the url
        specDummyrestapi.pathParam("pp1", "create");

        //set the request body
        DummyDataPojo requestBody = new DummyDataPojo("Perfect image", "Tom Hanks", 111111, 23);
        DummyResponsePojo expectedBody = new DummyResponsePojo(requestBody, "Successfully! Record has been added.", "success");

        //send the post request, get the response
        Response response = given().spec(specDummyrestapi).contentType(ContentType.JSON).body(requestBody).when().post("{pp1}");

        DummyResponsePojo actualBody = JsonUtil.convertJsonToJavaObject(response.asString(), DummyResponsePojo.class);

        //do assertions
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedBody.getStatus(), actualBody.getStatus());
        assertEquals(expectedBody.getData().getProfile_image(), actualBody.getData().getProfile_image());
        assertEquals(expectedBody.getData().getEmployee_name(), actualBody.getData().getEmployee_name());
        assertEquals(expectedBody.getData().getEmployee_salary(), actualBody.getData().getEmployee_salary());
        assertEquals(expectedBody.getData().getEmployee_age(), actualBody.getData().getEmployee_age());
        assertEquals(expectedBody.getMessage(), actualBody.getMessage());
    }


}
