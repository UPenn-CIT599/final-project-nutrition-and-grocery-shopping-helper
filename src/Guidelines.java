
public class Guidelines {

	public static void main(String[] args) {
		// File prep: Save in Excel as CSV then open in TextEdit and "zap gremlins"
		CalorieFileReader cfr = new CalorieFileReader("CalorieNeeds.csv");
		NutritionFileReader nfr = new NutritionFileReader("NutritionalGoals.csv");
		System.out.println(nfr.getColumnFromAge(1, "Female"));
		System.out.println(nfr.getColumnFromAge(4, "Female"));
		System.out.println(nfr.getColumnFromAge(10, "Female"));
		System.out.println(nfr.getColumnFromAge(1, "Male"));
		System.out.println(nfr.getColumnFromAge(4, "male"));
		System.out.println(nfr.getColumnFromAge(10, "male"));
 
	}

}
