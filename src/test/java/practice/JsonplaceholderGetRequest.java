package practice;

import base_urls.JsonplaceholderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import testDataDeposu.JsonplaceholderTestData;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class JsonplaceholderGetRequest extends JsonplaceholderBaseUrl {

    @Test
    public void get01() {
        //url ve request body olustur
        specJsonplaceholder.pathParams("pp1", "posts", "pp2", 22);

        //expected data olustur
        JsonplaceholderTestData jsonplaceholder = new JsonplaceholderTestData();

        JSONObject expBody = jsonplaceholder.expectedDataOlustur();


        //response kaydet

        Response response = given().spec(specJsonplaceholder).when().get("{pp1}/{pp2}");

        //do assertions

        JsonPath responseJSPath = response.jsonPath();

        assertEquals(jsonplaceholder.basariliStatusKod, response.statusCode());


        assertEquals(expBody.getInt("userId"), responseJSPath.getInt("userId"));
        assertEquals(expBody.getInt("id"), responseJSPath.getInt("id"));
        assertEquals(expBody.getString("title"), responseJSPath.getString("title"));
        assertEquals(expBody.getString("body"), responseJSPath.getString("body"));


    }
}
