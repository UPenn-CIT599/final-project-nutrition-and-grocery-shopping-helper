import java.util.ArrayList;

/**
 * Represents a FoodItem retrieved from the USDA Food Database
 */
public class FoodItem {
    public String name;
    public String uniqueIdentifier;
    public ArrayList<Nutrient> nutrients;
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
    
    /**
     * Used to add a nutrient to the ArrayList of nutrients for this FodItem.
     * @param nutrient
     */
    public void addNutrient(Nutrient nutrient) {
    	nutrients.add(nutrient);
    }
    
    public String findMatchingNutrient(String nutrient) {
    	String foodItemNutrient = "not found";
    	for (Nutrient ntrt : nutrients ) {
    		
    		
    	}
    	return foodItemNutrient;
    }
    
    
}
