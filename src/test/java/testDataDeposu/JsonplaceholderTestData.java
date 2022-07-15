package testDataDeposu;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class JsonplaceholderTestData {

    public int basariliStatusKod = 200;
    public int expectedPutStatusCode = 200;

    public int expectedPatchStatusCode = 200;

    public JSONObject expectedDataOlustur() {

        JSONObject body = new JSONObject();

        body.put("userId", 3);
        body.put("id", 22);
        body.put("title", "dolor sint quo a velit explicabo quia nam");
        body.put("body", "eos qui et ipsum ipsam suscipit aut\nsed omnis non odio\nexpedita earum mollitia molestiae aut atque rem suscipit\nnam impedit esse");

        return body;
    }

    public Map<String, Object> expectedDataWithAllKeys(Integer userId, String title, Boolean completed) {
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", userId);
        expectedData.put("title", title);
        expectedData.put("completed", completed);


        return expectedData;
    }

    public Map<String, Object> expectedDataWithMissingKeys(Integer userId, String title, Boolean completed) {
        Map<String, Object> expectedData = new HashMap<>();
        if (userId != null) {
            expectedData.put("userId", userId);
        }
        if (title != null) {
            expectedData.put("title", title);
        }
        if (completed != null) {
            expectedData.put("completed", completed);
        }

        return expectedData;
    }
}
