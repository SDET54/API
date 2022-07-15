package get_requests;

import base_urls.GorestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get11 extends GorestBaseUrl {

    /*
    Given
            https://gorest.co.in/public/v1/users
        When
            User send GET Request
        Then
            The value of "pagination limit" is 10
        And
            The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
        And
            The number of users should  be 10
        And
            We have at least one "active" status
        And
            "Kamla Chaturvedi", "Shresth Nehru", "Sitara Johar" are among the users
        And
            The female users are more than or equals male users

     */

    @Test
    public void get01() {
        //set the url
        specGorest.pathParams("pp1", "public", "pp2", "v1", "pp3", "users");

        //set the expected data

        //send the request, get the response
        Response response = given().spec(specGorest).when().get("{pp1}/{pp2}/{pp3}");
        //response.prettyPrint();

        //do assertions
        response.
                then().
                assertThat().
                body("meta.pagination.limit", equalTo(10),
                        "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"),
                        "data.id", hasSize(10),
                        "data.status", hasItem("active"),
                        "data.name", hasItems("Kamla Chaturvedi", "Shresth Nehru", "Sitara Johar"));

        JsonPath jsonPath = response.jsonPath();

        List<String> dataGenders = jsonPath.getList("data.gender");
        int male = 0;
        int female = 0;
        for (String s : dataGenders) {
            if (s.equalsIgnoreCase("male")) {
                male += 1;
            } else if (s.equalsIgnoreCase("female")) {
                female += 1;
            }
        }
        assertTrue(female >= male);

        //2.way
        List<String> femaleList = jsonPath.getList("data.findAll{it.gender=='female'}.gender");
        List<String> maleList = jsonPath.getList("data.findAll{it.gender=='male'}.gender");
        assertTrue(femaleList.size() >= maleList.size());
    }
}
