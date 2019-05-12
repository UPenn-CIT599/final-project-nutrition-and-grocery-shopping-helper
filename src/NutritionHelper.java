/**
 * This is the main entry class for this program
 * It should orchestrate the actions of other classes
 */
public class NutritionHelper {
    public static void main(String[] args) {
        // dependencies to identify a food
        GroceryImageClassification classifier = new GroceryImageClassification();

        // dependencies to search the API
        FoodDatabaseAPI foodDBAPI = new FoodDatabaseAPI();
        LevenshteinDistance ld = new LevenshteinDistance();
        FoodSearch foodSearch = new FoodSearch(foodDBAPI, ld);

        // dependencies to compare nutrition information
        ProfileCreator pc = new ProfileCreator();

        // STEP 1: CLASSIFY IMAGES IN DATA DIRECTORY
        String searchString = classifier.classify();
        System.out.println("Our classifier has identified your picture as a " + searchString);


        // STEP 2: CALL USDA DATABASE
        // get foodItem from USDA API based using classified tag as search string
        FoodItem foodItem = foodSearch.getFoodItem(searchString);

        // STEP 3: OFFER PERSONAL RECOMMENDATION
        // ask some questions to create Person instance
        Person profile = pc.createProfile();
        Guidelines.giveGuidelines(foodItem, profile);
    }
}
