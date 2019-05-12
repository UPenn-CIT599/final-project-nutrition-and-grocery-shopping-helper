public class Guidelines {
    /**
     * Walks user through how this FoodItem would affect their diet goals
     *
     * @param foodItem FoodItem returned from USDA Database
     */
    public static void giveGuidelines(FoodItem foodItem, Person user) {
        // File prep: Save in Excel as CSV then open in TextEdit and "zap gremlins"
        CalorieFileReader cfr = new CalorieFileReader("CalorieNeeds.csv");
        NutritionFileReader nfr = new NutritionFileReader("NutritionalGoals.csv");

        // See what matches between API and RDA recommendations
        System.out
                .println("One serving of this food item, " + foodItem.name + ", provides these nutrients:");

        for (Nutrient ntrt : foodItem.nutrients) {
            String units;
            String requirement = "";
            String nutrient = ntrt.getSimpleName();
            if (nutrient.contains("calories")) {
                nutrient = "calories";
                int requirementInt = cfr.getCalories(user.age, user.gender, user.activityLevel);
                requirement = Integer.toString(requirementInt);
                System.out.println(requirementInt + " " + user.age + " " + user.gender + " " + user.activityLevel);
                units = "kcal";
            } else {
                requirement = nfr.getNutrientRequirement(user.age, user.gender, nutrient);
                units = nfr.getNutrientUnit(nutrient);
            }
            if (ntrt.getValue() > 0.0) {
                System.out.println(ntrt.getName() + " " + ntrt.getValue() + " " + ntrt.getUnits() + ", you need "
                        + requirement + " " + units);
            }
        }
        System.out.println(" ");
    }
}
