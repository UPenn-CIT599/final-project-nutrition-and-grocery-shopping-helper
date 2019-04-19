import org.json.*;

/**
 * Represents the USDA Food Products Database REST API
 * https://ndb.nal.usda.gov/ndb/foods
 */
public class FoodDatabaseAPI {
    public JSONObject searchForFood(String searchString) {
        String apiKey = readAPIKeyFromFile();
        return makeAPIRequest(apiKey);
    }

    /**
     * Reads USDA API Key for a file in project root
     */
    private String readAPIKeyFromFile() {
        return "";
    }

    /**
     * Plumbing method to actually make the HTTP request and get the response
     */
    private JSONObject makeAPIRequest(String apiKey) {
        return new JSONObject();
    }

}
