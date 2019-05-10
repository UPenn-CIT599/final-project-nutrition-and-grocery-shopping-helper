
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
	
}
