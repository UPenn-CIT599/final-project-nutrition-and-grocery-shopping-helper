/**
 * Represents a FoodItem retrieved from the USDA Food Database
 */
public class FoodItem {
    public String name;
    public Integer uniqueIdentifier;
    public Integer calories;
    public Integer gramsProtein;
    public Integer gramsFat;
    public Integer gramsCarbs;

    public FoodItem(String name) {
        this.name = name;
    }

    public FoodItem(String name, Integer uniqueIdentifier, Integer calories, Integer gramsProtein, Integer gramsFat, Integer gramsCarbs) {
        this.name = name;
        this.uniqueIdentifier = uniqueIdentifier;
        this.calories = calories;
        this.gramsProtein = gramsProtein;
        this.gramsFat = gramsFat;
        this.gramsCarbs = gramsCarbs;
    }

    public Double percentCaloriesFromFat() {
        return (gramsFat * 9.0) / calories;
    }

    // TODO create some data structure to hold mineral amounts
}
