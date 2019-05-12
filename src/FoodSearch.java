import java.util.ArrayList;
import java.util.List;

public class FoodSearch {
    private FoodDatabaseAPI api;
    private LevenshteinDistance distanceCalculator;

    public FoodSearch(FoodDatabaseAPI api, LevenshteinDistance distanceCalculator) {
        this.api = api;
        this.distanceCalculator = distanceCalculator;
    }

    /**
     *
     * @param searchString used as query param for search API i.e "banana", "Tropicana Orange Juice"
     * @return FoodItem that is "closest" to search string
     */
    public FoodItem getFoodItem(String searchString) {
        // we add the term "raw" to bias the API query to find raw fruits/ vegetables
        // since most items in this database are prepared food products
        List<FoodContext> possibleItems = api.searchForFood(searchString + "+raw", 10);
        FoodContext closestItem = getClosestDistanceFood(possibleItems, searchString);
        return api.getFoodDetails(closestItem);
    }

    /**
     *
     * @param foods List of food name-uniqueId pairs from the search API
     * @param searchString query string used in the search API call
     * @return closest food based on Levenshtien distance from the search string
     */
    private FoodContext getClosestDistanceFood(List<FoodContext> foods, String searchString) {
        ArrayList<String> names = new ArrayList<>();
        for (int i = 0; i < foods.size(); i++) {
            names.add(foods.get(i).name);
        }
        int idxOfClosestFood = distanceCalculator.getIndexClosestWord(searchString, names);
        return foods.get(idxOfClosestFood);
    }
}
