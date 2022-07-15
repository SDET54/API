package testDataDeposu;

import java.util.HashMap;
import java.util.Map;

public class HerokuappTestData {

    public int expectedStatusCode = 200;

    public Map<String, String> bookingdates(String checkin, String checkout) {
        Map<String, String> bookingDates = new HashMap<>();
        bookingDates.put("checkin", checkin);
        bookingDates.put("checkout", checkout);

        return bookingDates;
    }

    public Map<String, Object> expectedData(String firstname, String lastname, int totalprice, boolean depositpaid, Map<String, String> bookingdates) {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", firstname);
        expectedData.put("lastname", lastname);
        expectedData.put("totalprice", totalprice);
        expectedData.put("depositpaid", depositpaid);
        expectedData.put("bookingdates", bookingdates);

        return expectedData;
    }
}
