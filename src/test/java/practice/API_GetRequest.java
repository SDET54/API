package practice;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class API_GetRequest extends JsonplaceholderBaseUrl {

    @Test
    public void get01() {

        //1. set the url
        specJsonplaceholder.pathParams("first", "posts", "second", 44);

        //2. set the expexted data(post-put-patch)

        //3. send the request and get the response

        Response response = given().spec(specJsonplaceholder).when().get("/{first}/{second}");
        // response.prettyPrint();

        //4. do assertion

        response.
                then().
                assertThat().statusCode(200).
                contentType(ContentType.JSON).
                body("userId", equalTo(5),
                        "title", equalTo("optio dolor molestias sit"));
    }
}
