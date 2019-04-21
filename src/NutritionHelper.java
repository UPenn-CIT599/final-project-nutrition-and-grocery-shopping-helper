import java.io.IOException;

/**
 * This is the main entry class for this program
 * It should orchestrate the actions of other classes
 */
public class NutritionHelper {
    public static void main(String[] args) {
        // dependencies to identify a food
        KerasInceptionV3Net classifier;
        try {
            classifier = new KerasInceptionV3Net("fake", "fake");
        } catch (IOException e) {
            System.out.println("unable to find model/ weights file");
        }

        // dependencies to search the API
        FoodDatabaseAPI foodDBAPI = new FoodDatabaseAPI();
        FoodSearch foodSearch = new FoodSearch(foodDBAPI);

        // dependencies to compare nutrition information
        NutritionFileReader nutritionReader = new NutritionFileReader("fake");
        CalorieFileReader calorieReader = new CalorieFileReader("fake");
        Guidelines guidelines = new Guidelines();
        Person person = new Person(12, "M", "High");
        Label label = new Label(123, 432);

        /* Actual application flow should go something like this:
            String searchString = classifier.classify("imagePath);
            FoodItem foodItem = foodSearch.getFoodItem(searchString);
            Int score = label.compareTo(decide which subset of nutrient we want to compare)
            // do some useful logging for the user to see how food affects them.
        */
    }
}
