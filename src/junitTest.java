import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class junitTest {
	// Initialize
	CalorieFileReader cfr = new CalorieFileReader("CalorieNeeds.csv");
	NutritionFileReader nfr = new NutritionFileReader("NutritionalGoals.csv");

	@Test
	void checkCaloriesFileLoaded() {
		// Test age / calories
		assertEquals(1000, cfr.getCalories(2, "Male", "Sedentary"));
		assertEquals(3000, cfr.getCalories(20, "Male", "Active"));
		assertEquals(2400, cfr.getCalories(52, "Male", "Moderate"));
		assertEquals(2200, cfr.getCalories(102, "Male", "Moderate"));
		assertEquals(1000, cfr.getCalories(3, "Female", "Sedentary"));
		assertEquals(1800, cfr.getCalories(77, "Female", "Moderate"));
		assertEquals(2200, cfr.getCalories(31, "Female", "Active"));
		assertEquals(1000, cfr.getCalories(0, "Female", "Active"));
	}
	
	@Test
	void checkNutiritionFileLoaded() {
		// Test age column return
		assertEquals(0, nfr.getColumnFromAge(1, "Female"));
		assertEquals(1, nfr.getColumnFromAge(4, "Female"));
		assertEquals(0, nfr.getColumnFromAge(1, "male"));
		assertEquals(2, nfr.getColumnFromAge(4, "male"));
		assertEquals(3, nfr.getColumnFromAge(10, "Female"));
		assertEquals(4, nfr.getColumnFromAge(10, "male"));
		assertEquals(3, nfr.getColumnFromAge(13, "Female"));
		assertEquals(4, nfr.getColumnFromAge(13, "male"));
		assertEquals(5, nfr.getColumnFromAge(14, "Female"));
		assertEquals(6, nfr.getColumnFromAge(14, "male"));
		assertEquals(7, nfr.getColumnFromAge(19, "Female"));
		assertEquals(8, nfr.getColumnFromAge(19, "male"));
		assertEquals(9, nfr.getColumnFromAge(31, "Female"));
		assertEquals(10, nfr.getColumnFromAge(31, "male"));
		assertEquals(9, nfr.getColumnFromAge(50, "Female"));
		assertEquals(10, nfr.getColumnFromAge(50, "male"));
		assertEquals(11, nfr.getColumnFromAge(101, "Female"));
		assertEquals(12, nfr.getColumnFromAge(101, "male"));
	}

	@Test
	void checkNutrientNameMatching() {
		assertEquals(0, nfr.getNutrientRow("Protein g"));
		assertEquals(1, nfr.getNutrientRow("Protein %kcal"));
		assertEquals(2, nfr.getNutrientRow("Carbohydrate g"));
		assertEquals(3, nfr.getNutrientRow("Carbohydrate %kcal"));
		assertEquals(4, nfr.getNutrientRow("fiber"));
		assertEquals(5, nfr.getNutrientRow("sugar"));
		assertEquals(6, nfr.getNutrientRow("Total fat"));
		assertEquals(7, nfr.getNutrientRow("Saturated fat"));
		assertEquals(8, nfr.getNutrientRow("Linoleic"));
		assertEquals(9, nfr.getNutrientRow("Linolenic"));
		assertEquals(10, nfr.getNutrientRow("Calcium"));
		assertEquals(11, nfr.getNutrientRow("Iron"));
		assertEquals(12, nfr.getNutrientRow("Magnesium"));
		assertEquals(13, nfr.getNutrientRow("Phosphorus"));
		assertEquals(14, nfr.getNutrientRow("Potassium"));
		assertEquals(15, nfr.getNutrientRow("Sodium"));
		assertEquals(16, nfr.getNutrientRow("Zinc"));
		assertEquals(17, nfr.getNutrientRow("Copper"));
		assertEquals(18, nfr.getNutrientRow("Manganese"));
		assertEquals(19, nfr.getNutrientRow("Selenium"));
		assertEquals(20, nfr.getNutrientRow("Vitamin A"));
		assertEquals(21, nfr.getNutrientRow("Vitamin E"));
		assertEquals(22, nfr.getNutrientRow("Vitamin D"));
		assertEquals(23, nfr.getNutrientRow("Vitamin C"));
		assertEquals(24, nfr.getNutrientRow("Thiamin"));
		assertEquals(25, nfr.getNutrientRow("Riboflavin"));
		assertEquals(26, nfr.getNutrientRow("Niacin"));
		assertEquals(27, nfr.getNutrientRow("Vitamin B6"));
		assertEquals(28, nfr.getNutrientRow("Vitamin B12"));
		assertEquals(29, nfr.getNutrientRow("Choline"));
		assertEquals(30, nfr.getNutrientRow("Vitamin K"));
		assertEquals(31, nfr.getNutrientRow("Folate"));
		assertEquals(-1, nfr.getNutrientRow("junk food"));
	}
	
	@Test	
	void checkGetNutrientRequirement() {
		assertEquals("13", nfr.getNutrientRequirement(1, "Female", "Protein g"));
		assertEquals("5-20", nfr.getNutrientRequirement(1, "Female", "Protein %kcal"));
		assertEquals("130", nfr.getNutrientRequirement(10, "Female", "Carbohydrate g"));
		assertEquals("45-65", nfr.getNutrientRequirement(10, "Female", "Carbohydrate %kcal"));
		assertEquals("22.4", nfr.getNutrientRequirement(10, "Female", "fiber"));

		
	}

}
