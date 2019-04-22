import java.util.List;

public class FoodSearch {
    private FoodDatabaseAPI api;

    public FoodSearch(FoodDatabaseAPI api) {
        this.api = api;
    }

    /**
     *
     * @param searchString used as query param for search API i.e "banana", "Tropicana Orange Juice"
     * @return FoodItem that is "closest" to search string
     */
    public FoodItem getFoodItem(String searchString) {
        List<FoodContext> possibleItems = api.searchForFood(searchString);
        FoodContext closestItem = getClosestDistanceFood(possibleItems, searchString);
        return api.getFoodDetails(closestItem);
    }

    /**
     *
     * @param foods List of food name-uniqueId pairs from the search API
     * @param searchString query string used in the search API call
     * @return closest food based on Levenshtien distance from the search string
     * TODO: if there is time I'd like to implement a smarter sort algo than string distance
     */
    private FoodContext getClosestDistanceFood(List<FoodContext> foods, String searchString) {
        // stubbed
        return new FoodContext("fake", "fake");
    }
}
