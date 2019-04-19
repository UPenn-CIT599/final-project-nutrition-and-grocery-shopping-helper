import org.json.JSONObject;

public class FoodSearch {
    private FoodDatabaseAPI api;

    public FoodItem getFoodItem(String searchString) {
        JSONObject foodItemResponse = api.searchForFood(searchString);
        // TODO this method is stubbed for now
        return new FoodItem(searchString);
    }
}
