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
    
    /**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the uniqueIdentifier
	 */
	public String getUniqueIdentifier() {
		return uniqueIdentifier;
	}

	/**
	 * @return the nutrients
	 */
	public ArrayList<Nutrient> getNutrients() {
		return nutrients;
	}

	/**
	 * @return the calories
	 */
	public Double getCalories() {
		return calories;
	}

	/**
     * Given a nutrient name, returns the nutrient object.
     * @param nutrient
     * @return
     */
    public Nutrient findMatchingNutrient(String nutrient) {
    	for (Nutrient ntrt : nutrients ) {
    	    if (ntrt.getName() == nutrient) {
    	        return ntrt;
            }
    	}
    	return null;
    }
}
