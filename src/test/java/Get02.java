import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Get02 {

    @Test
    public void get01() {
        /*
        Given
            https://restful-booker.herokuapp.com/booking/1001
        When
            User send a GET Request to the url
        Then
            HTTP Status code should be 404
        And
            Status Line should be HTTP/1.1 404 Not Found
        And
            Response body contains "Not Found"
        And
            Response body does not contain "TechProEd"
        And
            Server is "Cowboy"
     */

        //i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/1005";

        //ii) Set the expected data(Post-Put-Patch)


        //iii) Type code to send request
        Response response = given().when().get(url);
        response.prettyPrint();

        //iv) Do Assertion
        response.
                then().
                assertThat().
                statusCode(404).
                statusLine("HTTP/1.1 404 Not Found");

        //Response Body'de bulunan spesifik bir veri nasil assert edilir:

        //assertTrue() methodu parantezin icindeki deger true ise testi gecirir.
        assertTrue(response.asString().contains("Not Found"));

        //Response Body'de bulunmayan spesifik bir veri nasil assert edilir:

        //assertFalse() methodu parantezin icindeki deger false ise testi gecirir.
        assertFalse(response.asString().contains("TechProEd"));

        //assertEquals() methodu parantez icindeki iki deger esit ise testi gecirir.
        Assert.assertEquals("Cowboy", response.header("Server"));

    }

}
