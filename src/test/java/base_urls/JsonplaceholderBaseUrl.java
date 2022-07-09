package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonplaceholderBaseUrl {

    protected RequestSpecification specJsonplaceholder;

    //@Before annotation'i kullandigimiz methodlar her Test methodundan once calistirilir.
    @Before
    public void setup() {
        specJsonplaceholder = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }

}
