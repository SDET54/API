package get_requests;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    1) Postman manuel api testi icin kullanilir.
    2) Api otomasyon testi icin Rest-Assured Library kullaniyoruz.
    3) Otomasyon kodlarinin yazimi icin su adimlari izliyoruz:
        a) gereksinimleri anlama
        b) test case'i yazma
           i) test case yazimi icin Gherkin Language kullaniyoruz.
              'Gherkin' bazi keyword'lere sahiptir, bunlar:
               x) Given: on kosul
               y) When: aksiyonlar-->Get, Put, ...
               z) Then: donutler-->Assert
               t) And: coklu islemler icin

        c) Testing kodunun yazimi
           i) Set the URL
           ii) Set the expected data(Post-Put-Patch)
           iii) Type code to send request
           iv) Do Assertion
     */

    /*
        Given
            https://restful-booker.herokuapp.com/booking/3
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be JSON
        And
            Status Line should be HTTP/1.1 200 OK
     */
    @Test
    public void get01() {
        //i) Set the URL
        String url = "https://restful-booker.herokuapp.com/booking/333";

        //ii) Set the expected data(Post-Put-Patch)


        //iii) Type code to send request
        Response response = given().when().get(url);

        response.prettyPrint();

        //iv) Do Assertion

        response.
                then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                statusLine("HTTP/1.1 200 OK");

        //'Status Code' nasil yazdirilir:
        System.out.println("status code: " + response.statusCode());

        //'Content Type' nasil yazdirilir:
        System.out.println("content type: " + response.contentType());

        //'Status Line' nasil yazdirilir:
        System.out.println("status line: " + response.statusLine());

        //'Header' nasil yazdirilir:
        System.out.println(response.header("Accept-Encoding"));

        //'Headers' nasil yazdirilir:
        System.out.println("headers:\n" + response.headers());

        //'Time' nasil yazdirilir:
        System.out.println("time: " + response.getTime());
    }

}
