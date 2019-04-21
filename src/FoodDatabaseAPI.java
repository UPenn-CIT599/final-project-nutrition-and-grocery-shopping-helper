import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import org.json.*;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the USDA Food Products Database REST API
 * https://ndb.nal.usda.gov/ndb/foods
 */
public class FoodDatabaseAPI {
    public static void main(String[] args) {
        FoodDatabaseAPI api = new FoodDatabaseAPI();
        api.searchForFood("banana");
    }

    private static String API_URL = "https://api.nal.usda.gov/ndb/";
    private String apiKey;

    public FoodDatabaseAPI() {
        try {
            apiKey = readAPIKeyFromFile();
        } catch (IOException e) {
            System.out.println("Exception thrown trying to read API key from file");
            e.printStackTrace();
        }
    }

    /**
     *
     * @param searchString name of the food to query the "Food Search API" for
     * @return List of FoodContext objects, used as a param for foodDetails method
     */
    public List<FoodContext> searchForFood(String searchString) {
        JSONObject jsonResponse =  makeSearchRequest(searchString);
        ArrayList<FoodContext> result = new ArrayList<>();
        try {
            JSONObject list = jsonResponse.getJSONObject("list");
            JSONArray items = list.getJSONArray("item");
            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String name = item.getString("name");
                // unique database id for this food
                String databaseId = item.getString("ndbno");
                result.add(new FoodContext(name, databaseId));
            }

        } catch (JSONException e) {
            System.out.println("Exception thrown accessing search API, returning empty list");
            e.printStackTrace();
        }
        return result;
    }

    /**
     *
     * @param food FoodContext object containing the name/ unique identifier from the searchForFood method
     * @return FoodItem containing nutrient information for a specific food
     */
    public FoodItem getFoodDetails(FoodContext food) {
        // stubbed for now
        return new FoodItem("fake name");
    }

    /**
     * Reads USDA API Key for a file in project root
     */
    private String readAPIKeyFromFile() throws IOException {
        File file = new File("apiKey.txt");

        Scanner s = new Scanner(file);
        String apiKey = s.nextLine();
        if (apiKey == "DEMO_KEY") {
            throw new IOException("you need to paste a real API Key for USDA API in apiKey.txt");
        }
        return apiKey;
    }

    private JSONObject makeSearchRequest(String searchString) {
        String searchUrl = (API_URL + "/search/?format=json&q=" + searchString + "&api_key=" + apiKey);
        System.out.println("searchUrl" + searchUrl);
        try {
            return makeAPICall(searchUrl);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private JSONObject makeAPICall(String urlString) throws IOException, JSONException {
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;

        StringBuffer response = new StringBuffer();
        //BufferedReader does not have a "hasNext" type method so this is how to check for
        //more input
        //if it has more input append to the StringBuffer
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return new JSONObject(response.toString());
    }

}
