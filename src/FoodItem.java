import java.util.ArrayList;

/**
 * Represents a FoodItem retrieved from the USDA Food Database
 */
public class FoodItem {
    public String name;
    public String uniqueIdentifier;
    public ArrayList<Nutrient> nutrients = new ArrayList<>();
    public Double calories;

    public FoodItem(String name, String uniqueIdentifier) {
        this.name = name;
        this.uniqueIdentifier = uniqueIdentifier;
    }

    @Override
    public String toString() {
        return "FoodItem: " + name + " with " + calories + " calories";
    }
    
    /**
     * Used to add a nutrient to the ArrayList of nutrients for this FoodItem.
     * @param nutrient
     */
    public void addNutrient(Nutrient nutrient) {
    	nutrients.add(nutrient);
    }
    
    public Nutrient findMatchingNutrient(String nutrient) {
    	for (Nutrient ntrt : nutrients ) {
    	    if (ntrt.getName() == nutrient) {
    	        return ntrt;
            }
    	}
    	return null;
    }
}
