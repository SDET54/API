package testDataDeposu;

import java.util.HashMap;
import java.util.Map;

public class GorestTestData {

    public int expectedStatusCode = 200;

    public Map<String, String> dataMap(String name, String email, String gender, String status) {
        Map<String, String> dataMap = new HashMap<>();
        dataMap.put("name", name);
        dataMap.put("email", email);
        dataMap.put("gender", gender);
        dataMap.put("status", status);

        return dataMap;
    }

    public Map<String, Object> expectedData(Object meta, Map<String, String> data) {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("meta", meta);
        expectedData.put("data", data);

        return expectedData;
    }
}
