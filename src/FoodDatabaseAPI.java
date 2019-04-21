import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.*;
import org.apache.http.client.*;

import java.io.IOException;

/**
 * Represents the USDA Food Products Database REST API
 * https://ndb.nal.usda.gov/ndb/foods
 */
public class FoodDatabaseAPI {
    private static String API_URL = "https://api.nal.usda.gov/ndb/";
    private CloseableHttpClient client;
    private String apiKey;

    public FoodDatabaseAPI() {
        client = HttpClientBuilder.create().build();
        apiKey = readAPIKeyFromFile();
    }

    public JSONObject searchForFood(String searchString) {
        String apiKey = readAPIKeyFromFile();
        return makeSearchRequest(apiKey);
    }

    /**
     * Reads USDA API Key for a file in project root
     */
    private String readAPIKeyFromFile() {
        return "";
    }

    private JSONObject makeSearchRequest(String searchString) {
        RequestBuilder.get(API_URL + "/search/format=json&q=" + searchString + "&api_key=" + apiKey);
        HttpGet getRequest = new HttpGet(API_URL + "/search/format=json&q=" + searchString + "&api_key=" + apiKey);
        try {
            CloseableHttpResponse response = client.execute(getRequest);
            System.out.println(response.getStatusLine().getStatusCode());
            response.close();
            EntityUtils
            return new JSONObject();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
        }
    }

}
