package get_requests;

import base_urls.DummyrestapiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import pojos.DummyrestapiPojos.DummyDataPojo;
import pojos.DummyrestapiPojos.DummyResponsePojo;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyrestapiBaseUrl {
    /*
       URL: https://dummy.restapiexample.com/api/v1/employee/1
       HTTP Request Method: GET Request
       Test Case: Type by using Gherkin Language
       Assert: 	i) Status code is 200
               ii) "employee_name" is "Tiger Nixon"
              iii) "employee_salary" is 320800
               iv)  "employee_age" is 61
                v) "status" is "success"
               vi)  "message" is "Successfully! Record has been fetched."
     */

    /*
      Given
          https://dummy.restapiexample.com/api/v1/employee/1
      When
          send the request to url
      Then
          Status code is 200
      And
          "employee_name" is "Tiger Nixon"
      And
          "employee_salary" is 320800
      And
          "status" is "success"
      And
          "message" is "Successfully! Record has been fetched."


     */


    @Test
    public void get01() {
        //set the url
        specDummyrestapi.pathParams("pp1", "employee", "pp2", 1);

        //set the expected data
        DummyDataPojo dataBody = new DummyDataPojo("", "Tiger Nixon", 320800, 1, 61);
        DummyResponsePojo expectedBody = new DummyResponsePojo(dataBody, "Successfully! Record has been fetched.", "success");

        //send the request, get the response
        Response response = given().spec(specDummyrestapi).contentType(ContentType.JSON).when().get("{pp1}/{pp2}");
        DummyResponsePojo actualBody = response.as(DummyResponsePojo.class);

        //do assertions
        //1.way
        assertEquals(expectedBody.getMessage(), actualBody.getMessage());
        assertEquals(expectedBody.getStatus(), actualBody.getStatus());
        assertEquals(expectedBody.getData().getEmployee_age(), actualBody.getData().getEmployee_age());
        assertEquals(expectedBody.getData().getEmployee_name(), actualBody.getData().getEmployee_name());
        assertEquals(expectedBody.getData().getId(), actualBody.getData().getId());
        assertEquals(expectedBody.getData().getEmployee_salary(), actualBody.getData().getEmployee_salary());
        assertEquals(expectedBody.getData().getProfile_image(), actualBody.getData().getProfile_image());


        //2.way
        response.then().assertThat().
                statusCode(200).
                body("data.employee_name", equalTo("Tiger Nixon"),
                        "data.employee_salary", equalTo(320800),
                        "status", equalTo("success"),
                        "message", equalTo("Successfully! Record has been fetched."));

    }
}
