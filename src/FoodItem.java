/**
 * Represents a FoodItem retrieved from the USDA Food Database
 */
public class FoodItem {
    public String name;
    public String uniqueIdentifier;
    public Double calories;
    public Double gramsProtein;
    public Double gramsFat;
    public Double gramsCarbs;

    public FoodItem(String name) {
        this.name = name;
    }

    public FoodItem(String name, String uniqueIdentifier, Double calories, Double gramsProtein, Double gramsFat, Double gramsCarbs) {
        this.name = name;
        this.uniqueIdentifier = uniqueIdentifier;
        this.calories = calories;
        this.gramsProtein = gramsProtein;
        this.gramsFat = gramsFat;
        this.gramsCarbs = gramsCarbs;
    }

    public double percentCaloriesFromFat() {
        return 100 * ((gramsFat * 9) / calories);
    }

    @Override
    public String toString() {
        return "FoodItem: " + name + " with " + calories + " calories";
    }
}
