package practice;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class API_PostRequest extends JsonplaceholderBaseUrl {

    @Test
    public void post01() {
        /*
        https://jsonplaceholder.typicode.com/posts URL'ine
        asagidaki body ile bir POST request gonderdigimizde

        {
        "title": "API",
        "body": "API ogrenmek ne guzel",
        "userID": 10
        }

        donen Response'un;
        status code'nun 201,
        content type/nin application/json,
        ve Response Body'sinin id haric,
         {
        "title": "API",
        "body": "API ogrenmek ne guzel",
        "userID": 10
        }
        oldugunu test edin
         */

        //1. set the url and request
        specJsonplaceholder.pathParams("first", "posts");

        JSONObject reqBody = new JSONObject();

        reqBody.put("title", "API");
        reqBody.put("body", "API ogrenmek ne guzel");
        reqBody.put("userID", 10);

        //2. set the expected data

        JSONObject expBody = new JSONObject();

        expBody.put("title", "API");
        expBody.put("body", "API ogrenmek ne guzel");
        expBody.put("userID", 10);

        //3. save response

        Response response = given().spec(specJsonplaceholder).
                contentType(ContentType.JSON).
                when().
                body(reqBody.toString()).
                post("/{first}");

        JsonPath actBody = response.jsonPath();

        //4. Assertion

        response.
                then().
                assertThat().
                statusCode(201).
                contentType(ContentType.JSON);

        Assert.assertEquals(expBody.get("title"), actBody.get("title"));
        Assert.assertEquals(expBody.get("body"), actBody.get("body"));
        Assert.assertEquals(expBody.get("userID"), actBody.get("userID"));


    }
}
