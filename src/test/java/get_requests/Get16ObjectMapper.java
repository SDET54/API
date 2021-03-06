package get_requests;

import base_urls.DummyrestapiBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16ObjectMapper extends DummyrestapiBaseUrl {
    /*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees
                  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
                   iv) The greatest age is 66
                    v) The name of the lowest age is "Tatyana Fitzpatrick"
                   vi) Total salary of all employees is 6,644,770
    */

    /*
    Given
        https://dummy.restapiexample.com/api/v1/employees
    When
        user sends get request to url
    Then
        Status code is 200
    And
        There are 24 employees
    And
        "Tiger Nixon" and "Garrett Winters" are among the employees
    And
        The greatest age is 66
    And
        The name of the lowest age is "Tatyana Fitzpatrick"
    And
        Total salary of all employees is 6,644,770

     */

    @Test
    public void get01ObjectMapper() {
        //set the url
        specDummyrestapi.pathParam("pp1", "employees");

        //send the get request, get the response
        Response response = given().spec(specDummyrestapi).contentType(ContentType.JSON).when().get("{pp1}");

        //do assertions
        response.then().assertThat().
                statusCode(200). //Status code is 200
                body("data.id", hasSize(24)). //There are 24 employees
                body("data.employee_name", hasItems("Tiger Nixon", "Garrett Winters")); //"Tiger Nixon" and "Garrett Winters" are among the employees

        //The greatest age is 66
        List<Integer> ageList = response.jsonPath().getList("data.employee_age");
        int actualMaxAge = ageList.stream().reduce(Integer.MIN_VALUE, (x, y) -> x > y ? x : y);
        assertEquals(66, actualMaxAge);

        //The name of the lowest age is "Tatyana Fitzpatrick"
        int actualMinAge = ageList.stream().reduce(Integer.MAX_VALUE, (x, y) -> x > y ? y : x);
        String actualMinAgeName = response.jsonPath().getString("data.findAll{it.employee_age==" + actualMinAge + "}.employee_name").replace("[", "").replace("]", "");
        assertEquals("Tatyana Fitzpatrick", actualMinAgeName);

        //Total salary of all employees is 6,644,770
        List<Integer> salaryList = response.jsonPath().getList("data.employee_salary");
        int actualTotalSalary = salaryList.stream().reduce(0, Integer::sum);
        assertEquals(6644770, actualTotalSalary);
    }
}
