package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Get05 extends HerokuappBaseUrl {

    /*
        Given
            https://restful-booker.herokuapp.com/booking
        When
            User send a request to the URL
        Then
            Status code is 200
	  	And
	  		Among the data there should be someone whose firstname is "Aaron" and last name is "Chen"
     */
    @Test
    public void get01() {
        //1. set the url
        specHerokuapp.
                pathParam("first", "booking").
                queryParams("firstname", "Aaron",
                        "lastname", "Chen");

        //2. set the expected data

        //3. send the request, get the response
        Response response = given().spec(specHerokuapp).when().get("/{first}");
        //response.prettyPrint();

        //4. do assertion
        response.
                then().
                assertThat().
                statusCode(200);

        List<Integer> responseListesi = response.jsonPath().getList("bookingid");
        //System.out.println(responseListesi);

        for (Integer integer : responseListesi) {
            response = given().spec(specHerokuapp).when().get("/{first}/" + integer);
            //assertResponse.prettyPrint();
            response.then().assertThat().body("firstname", equalTo("Aaron"),
                    "lastname", equalTo("Chen"));
            break;
        }

    }
}

