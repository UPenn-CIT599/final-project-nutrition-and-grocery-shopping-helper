import java.io.IOException;

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
        ProfileCreator pc = new ProfileCreator();

        // dependencies to compare nutrition information
        NutritionFileReader nutritionReader = new NutritionFileReader("NutritionalGoals.csv");
        CalorieFileReader calorieReader = new CalorieFileReader("CalorieNeeds.csv");
        Guidelines guidelines = new Guidelines();

        // STEP 1: CLASSIFY IMAGES IN DATA DIRECTORY
        String searchString = classifier.classify();
        System.out.println("Our classifier has identified your picture as a " + searchString);


        // STEP 2: CALL USDA DATABASE
        // get foodItem from USDA API based using classified tag as search string
        FoodItem foodItem = foodSearch.getFoodItem(searchString);

        // STEP 3: OFFER PERSONAL RECOMMENDATION
        // ask some questions to create Person instance
        Person profile = pc.createProfile();
        // print how the calories in this food would affect you
        int requiredCalories = calorieReader.getCalories(profile.age, profile.gender, profile.activityLevel);
        System.out.println(foodItem.calories);
        System.out.println(requiredCalories);
        double percentageCalories = foodItem.calories / (double) requiredCalories * 100.0;
        System.out.println("Eating this food item named \"" + foodItem.name + "\" represents " + Double.toString(percentageCalories).substring(0,4) + "% of your total calorie requirement.");

        Guidelines.main(new String[]{});
    }
}
