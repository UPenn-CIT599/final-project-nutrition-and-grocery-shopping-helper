
public class Nutrient {

	private String name;
	private String units;
	private int nutrientId; //nutrient_id
	private double value;
	
	public Nutrient(String n, String u, double v) {
		name = n;
		units = u;
		value = v;			
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the units
	 */
	public String getUnits() {
		return units;
	}

	/**
	 * @return the nutrientId
	 */
	public int getNutrientId() {
		return nutrientId;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}
	
	/**
	 * Returns the recommended in-take for a given person and nutrient.
	 * @param person
	 * @param nfr
	 * @return
	 */
	public String getRequirement(Person person, NutritionFileReader nfr) {
		int age = person.getAge();
		String gender = person.getGender();
		String requirement = nfr.getNutrientRequirement(age, gender, this.name);		
		return requirement;	
	}
	
	/**
	 * Returns a simplified version of the name of the nutrient.
	 * @param name
	 * @return
	 */
	public String getSimpleName() {
		String simpleName = name.toLowerCase();
		if (simpleName.contains("fat")) { simpleName = "fat"; }
		if (simpleName.contains("carbohydrate")) { simpleName = "carbohydrate"; }
		if (simpleName.contains("fiber")) { simpleName = "fiber"; }
		if (simpleName.contains("sugar")) { simpleName = "sugars"; }
		if (simpleName.contains("calcium")) { simpleName = "calcium"; }
		if (simpleName.contains("iron")) { simpleName = "iron"; }
		if (simpleName.contains("magnesium")) { simpleName = "magnesium"; }
		if (simpleName.contains("phosphorus")) { simpleName = "phosphorus"; }
		if (simpleName.contains("potassium")) { simpleName = "potassium"; }
		if (simpleName.contains("sodium")) { simpleName = "sodium"; }
		if (simpleName.contains("zinc")) { simpleName = "zinc"; }
		if (simpleName.contains("vitamin c")) { simpleName = "vitamin c"; }
		if (simpleName.contains("vitamin b-6")) { simpleName = "vitamin b6"; }
		if (simpleName.contains("folate")) { simpleName = "folate"; }
		if (simpleName.contains("vitamin b-12")) { simpleName = "vitamin b12"; }
		if (simpleName.contains("vitamin a")) { simpleName = "vitamin a"; }
		if (simpleName.contains("vitamin e")) { simpleName = "vitamin e"; }
		if (simpleName.contains("vitamin d")) { simpleName = "vitamin d"; }
		if (simpleName.contains("vitamin k")) { simpleName = "vitamin k"; }	
		return simpleName;
	}
	
}
