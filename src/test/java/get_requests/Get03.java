package get_requests;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Get03 extends JsonplaceholderBaseUrl {

    /*
        Given
            https://jsonplaceholder.typicode.com/todos/23
        When
            User send GET Request to the URL
        Then
            HTTP Status Code should be 200
		And
		    Response format should be “application/json”
		And
		    “title” is “et itaque necessitatibus maxime molestiae qui quas velit”,
		And
		    “completed” is false
		And
		    “userId” is 2
     */

    @Test
    public void get01() {
        //i) Set the URL
        // String url = "https://jsonplaceholder.typicode.com/todos/23"; //onerilmiyor

        spec.pathParams("first", "todos", "second", 23);

        //ii) Set the expected data(Post-Put-Patch)
        //iii) Send the request and get the Response

        Response response = given().spec(spec).when().get("/{first}/{second}");
        //response.prettyPrint();

        //iv) Do Assertion
        //1.yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit")).
                body("completed", equalTo(false)).
                body("userId", equalTo(2));

        //2.yol
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("title", equalTo("et itaque necessitatibus maxime molestiae qui quas velit"),
                        "completed", equalTo(false),
                        "userId", equalTo(2));

    }

    /*
    Note 1: Assertion yaparken Java calismayi durdurdugunda hata sonrasi kodlar calismaz.
            Boylece hata sonrasi assertion'lar hakkinda bilgi sahibi olamayiz.
            Fakat hata oncesi assertion'lar gecmistir.

     Note 2: Eger kodumuzu hata noktasinda duracak sekilde yazarsak "Hard Assertion" yapmis oluyoruz.

     Note 3: Eger kodumuzu hata noktasinda durmayacak sekilde yazarsak "Soft Assertion" yapmis oluyoruz.

     Note 4: Eger coklu body() methodu icinde assert yaparsak "Hard Assert",
             tek body() methodu icinde assert yaparsak "Soft Assert" yapmis oluyoruz.
     */
}
