package base_urls;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GorestBaseUrl {

    protected RequestSpecification specGorest;

    @Before
    public void setup(){
        specGorest = new RequestSpecBuilder().setBaseUri("https://gorest.co.in").build();
    }

}
