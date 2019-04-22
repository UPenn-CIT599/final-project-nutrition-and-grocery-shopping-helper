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
		assertEquals("<10%", nfr.getNutrientRequirement(13, "Female", "sugar"));
		assertEquals("25-35", nfr.getNutrientRequirement(13, "Female", "Total fat"));
		assertEquals("<10%", nfr.getNutrientRequirement(13, "Female", "Saturated fat"));
		assertEquals("12", nfr.getNutrientRequirement(13, "Male", "Linoleic"));
		assertEquals("1.2", nfr.getNutrientRequirement(13, "Male", "Linolenic"));
		assertEquals("1300", nfr.getNutrientRequirement(13, "Male", "Calcium"));
		assertEquals("11", nfr.getNutrientRequirement(17, "Male", "Iron"));
		assertEquals("410", nfr.getNutrientRequirement(17, "Male", "Magnesium"));
		assertEquals("1250", nfr.getNutrientRequirement(17, "Male", "Phosphorus"));
		assertEquals("4700", nfr.getNutrientRequirement(30, "Male", "Potassium"));
		assertEquals("2300", nfr.getNutrientRequirement(30, "Male", "Sodium"));
		assertEquals("8", nfr.getNutrientRequirement(31, "female", "Zinc"));
		assertEquals("900", nfr.getNutrientRequirement(31, "female", "Copper"));
		assertEquals("1.8", nfr.getNutrientRequirement(31, "female", "Manganese"));
		assertEquals("55", nfr.getNutrientRequirement(31, "female", "Selenium"));
		assertEquals("700", nfr.getNutrientRequirement(45, "female", "Vitamin A"));
		assertEquals("15", nfr.getNutrientRequirement(45, "female", "Vitamin E"));
		assertEquals("600", nfr.getNutrientRequirement(45, "female", "Vitamin D"));
		assertEquals("75", nfr.getNutrientRequirement(45, "female", "Vitamin C"));
		assertEquals("1.1", nfr.getNutrientRequirement(65, "female", "Thiamin"));
		assertEquals("1.1", nfr.getNutrientRequirement(65, "female", "Riboflavin"));
		assertEquals("14", nfr.getNutrientRequirement(65, "female", "Niacin"));
		assertEquals("1.7", nfr.getNutrientRequirement(65, "male", "Vitamin B6"));
		assertEquals("2.4", nfr.getNutrientRequirement(65, "male", "Vitamin B12"));
		assertEquals("550", nfr.getNutrientRequirement(95, "male", "Choline"));
		assertEquals("120", nfr.getNutrientRequirement(95, "male", "Vitamin K"));
		assertEquals("400", nfr.getNutrientRequirement(105, "female", "Folate"));
		assertEquals("Nutrient: junk food not found", nfr.getNutrientRequirement(105, "female", "junk food"));	
	}
	
	@Test
	void testGetNutrientUnit() {
		assertEquals("Protein g", nfr.getNutrientUnit("Protein g"));
		assertEquals("Protein %kcal", nfr.getNutrientUnit("Protein %kcal"));
		assertEquals("Carbohydrate g", nfr.getNutrientUnit("Carbohydrate g"));
		assertEquals("Carbohydrate %kcal", nfr.getNutrientUnit("Carbohydrate %kcal"));
		assertEquals("Dietary fiber g", nfr.getNutrientUnit("fiber"));
		assertEquals("Added sugars %kcal", nfr.getNutrientUnit("sugar"));
		assertEquals("Total fat %kcal", nfr.getNutrientUnit("Total fat"));
		assertEquals("Saturated fat %kcal", nfr.getNutrientUnit("Saturated fat"));
		assertEquals("Linoleic acid g", nfr.getNutrientUnit("Linoleic"));
		assertEquals("Linolenic acid g", nfr.getNutrientUnit("Linolenic"));
		assertEquals("Calcium mg", nfr.getNutrientUnit("Calcium"));
		assertEquals("Iron mg", nfr.getNutrientUnit("Iron"));
		assertEquals("Magnesium mg", nfr.getNutrientUnit("Magnesium"));
		assertEquals("Phosphorus mg", nfr.getNutrientUnit("Phosphorus"));
		assertEquals("Potassium mg", nfr.getNutrientUnit("Potassium"));
		assertEquals("Sodium mg", nfr.getNutrientUnit("Sodium"));
		assertEquals("Zinc mg", nfr.getNutrientUnit("Zinc"));
		assertEquals("Copper mcg", nfr.getNutrientUnit("Copper"));
		assertEquals("Manganese mg", nfr.getNutrientUnit("Manganese"));
		assertEquals("Selenium mcg", nfr.getNutrientUnit("Selenium"));
		assertEquals("Vitamin A mg RAE", nfr.getNutrientUnit("Vitamin A"));
		assertEquals("Vitamin E mg AT", nfr.getNutrientUnit("Vitamin E"));
		assertEquals("Vitamin D IU", nfr.getNutrientUnit("Vitamin D"));
		assertEquals("Vitamin C mg", nfr.getNutrientUnit("Vitamin C"));
		assertEquals("Thiamin mg", nfr.getNutrientUnit("Thiamin"));
		assertEquals("Riboflavin mg", nfr.getNutrientUnit("Riboflavin"));
		assertEquals("Niacin mg", nfr.getNutrientUnit("Niacin"));
		assertEquals("Vitamin B6 mg", nfr.getNutrientUnit("Vitamin B6"));
		assertEquals("Vitamin B12 mcg", nfr.getNutrientUnit("Vitamin B12"));
		assertEquals("Choline mg", nfr.getNutrientUnit("Choline"));
		assertEquals("Vitamin K mcg", nfr.getNutrientUnit("Vitamin K"));
		assertEquals("Folate mcg DFE", nfr.getNutrientUnit("Folate"));
		assertEquals("Nutrient: junk food not found", nfr.getNutrientUnit("junk food"));
	}

}
