package get_requests;

import base_urls.HerokuappBaseUrl;
import io.restassured.path.json.JsonPath;
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
	  		Among the data there should be someone whose firstname is "Zbigniew" and last name is "Angermann"
     */
    @Test
    public void get01() {
        //1. set the url
        specHerokuapp.
                pathParam("first", "booking");

        //2. set the expected data

        //3. send the request, get the response
        Response response = given().spec(specHerokuapp).when().get("{first}");
        //response.prettyPrint();

        //4. do assertion
        response.
                then().
                assertThat().
                statusCode(200);

        JsonPath rspJSPath = response.jsonPath();

        List<Integer> bookingidListesi = rspJSPath.getList("bookingid");
        //System.out.println(bookingidListesi);

        for (Integer bookingId : bookingidListesi) {
            response = given().spec(specHerokuapp).when().get("{first}/" + bookingId);
            //response.prettyPrint();

            rspJSPath = response.jsonPath();
            try {
                if (rspJSPath.get("firstname").equals("Zbigniew")) {
                    response.then().assertThat().body("lastname", equalTo("Angermann"));
                    System.out.println("booking id: " + bookingId);
                    break;
                }
            } catch (Exception e) {
                System.out.println(bookingId + " no-lu kayiti kontrol edin");
            }
        }
    }
}

