import java.util.List;
import java.util.stream.Collectors;

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
        List<FoodContext> possibleItems = api.searchForFood(searchString, 10);
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
        List<String> names = foods.stream().map(food -> food.name).collect(Collectors.toList());
        int idxOfClosestFood = distanceCalculator.getIndexClosestWord(searchString, names);
        return foods.get(idxOfClosestFood);
    }
}
