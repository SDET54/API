package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class DummyrestapiBaseUrl {

    protected RequestSpecification specDummyrestapi;

    @Before
    public void setup(){
        specDummyrestapi = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/api/v1").build();
    }
}
