
public class Guidelines {

	public static void main(String[] args) {
		// File prep: Save in Excel as CSV then open in TextEdit and "zap gremlins"
		CalorieFileReader cfr = new CalorieFileReader("CalorieNeeds.csv");
		NutritionFileReader nfr = new NutritionFileReader("NutritionalGoals.csv");

        FoodItem banana = new FoodItem("Bananas, raw");
        banana.addNutrient(new Nutrient("Protein" ,"g" , 1.37));
        banana.addNutrient(new Nutrient("Total lipid (fat)" ,"g" , 0.42));
        banana.addNutrient(new Nutrient("Carbohydrate, by difference" ,"g" , 28.78));
        banana.addNutrient(new Nutrient("Fiber, total dietary" ,"g" , 3.3));
        banana.addNutrient(new Nutrient("Sugars, total" ,"g" , 15.41));
        banana.addNutrient(new Nutrient("Calcium, Ca" ,"mg" , 6));
        banana.addNutrient(new Nutrient("Iron, Fe" ,"mg" , 0.33));
        banana.addNutrient(new Nutrient("Magnesium, Mg" ,"mg" , 34));
        banana.addNutrient(new Nutrient("Phosphorus, P" ,"mg" , 28));
        banana.addNutrient(new Nutrient("Potassium, K" ,"mg" , 451));
        banana.addNutrient(new Nutrient("Sodium, Na" ,"mg" , 1));
        banana.addNutrient(new Nutrient("Zinc, Zn" ,"mg" , 0.19));
        banana.addNutrient(new Nutrient("Vitamin C, total ascorbic acid" ,"mg" , 11));
        banana.addNutrient(new Nutrient("Thiamin" ,"mg" , 0.039));
        banana.addNutrient(new Nutrient("Riboflavin" ,"mg" , 0.092));
        banana.addNutrient(new Nutrient("Niacin" ,"mg" , 0.838));
        banana.addNutrient(new Nutrient("Vitamin B-6" ,"mg" , 0.462));
        banana.addNutrient(new Nutrient("Folate, DFE" ,"µg" , 25));
        banana.addNutrient(new Nutrient("Vitamin B-12" ,"µg" , 0));
        banana.addNutrient(new Nutrient("Vitamin A, RAE" ,"µg" , 4));
        banana.addNutrient(new Nutrient("Vitamin A, IU" ,"IU" , 81));
        banana.addNutrient(new Nutrient("Vitamin E (alpha-tocopherol)" ,"mg" , 0.13));
        banana.addNutrient(new Nutrient("Vitamin D (D2 + D3)" ,"µg" , 0));
        banana.addNutrient(new Nutrient("Vitamin D" ,"IU" , 0));
        banana.addNutrient(new Nutrient("Vitamin K (phylloquinone)" ," µg" , 0.6));

        
        FoodItem apple = new FoodItem("Apples, raw");
        apple.addNutrient(new Nutrient("Protein","g", 0.36));
        apple.addNutrient(new Nutrient("Total lipid (fat)","g", 0.17));
        apple.addNutrient(new Nutrient("Carbohydrate, by difference","g", 16.84));
        apple.addNutrient(new Nutrient("Fiber, total dietary","g", 1.7));
        apple.addNutrient(new Nutrient("Sugars, total","g", 13.33));
        apple.addNutrient(new Nutrient("Calcium, Ca","mg", 7));
        apple.addNutrient(new Nutrient("Iron, Fe","mg", 0.09));
        apple.addNutrient(new Nutrient("Magnesium, Mg","mg", 5));
        apple.addNutrient(new Nutrient("Phosphorus, P","mg", 15));
        apple.addNutrient(new Nutrient("Potassium, K","mg", 119));
        apple.addNutrient(new Nutrient("Sodium, Na","mg", 0));
        apple.addNutrient(new Nutrient("Zinc, Zn","mg", 0.07));
        apple.addNutrient(new Nutrient("Vitamin C, total ascorbic acid","mg", 5.3));
        apple.addNutrient(new Nutrient("Thiamin","mg", 0.025));
        apple.addNutrient(new Nutrient("Riboflavin","mg", 0.037));
        apple.addNutrient(new Nutrient("Niacin","mg", 0.12));
        apple.addNutrient(new Nutrient("Vitamin B-6","mg", 0.049));
        apple.addNutrient(new Nutrient("Folate, DFE","µg", 0));
        apple.addNutrient(new Nutrient("Vitamin B-12","µg", 0));
        apple.addNutrient(new Nutrient("Vitamin A, RAE","µg", 3));
        apple.addNutrient(new Nutrient("Vitamin A, IU","IU", 50));
        apple.addNutrient(new Nutrient("Vitamin E (alpha-tocopherol)","mg", 0.07));
        apple.addNutrient(new Nutrient("Vitamin D (D2 + D3)","µg", 0));
        apple.addNutrient(new Nutrient("Vitamin D","IU", 0));
        apple.addNutrient(new Nutrient("Vitamin K (phylloquinone)","µg", 0.8));
 
	}

}
