import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NutritionFileReader {
	private String[][] nutritionDataMale = new String[32][16];
	private String[][] nutritionDataFemale = new String[32][16];
	private int[] ageChartMale = new int[52];
	private int[] ageChartFemale = new int[52];
	private String[][] nutrientNames = new String[32][2];

	/**
	 * Given a nutrient, gender and age, this returns the recommended consumption. 
	 * Gender must be provided as male or female. Gender and nutrient are case 
	 * insensitive. Information for the following nutrients are available:
	 * 
	 * Protein g
	 * Protein %kcal
	 * Carbohydrate g
	 * Carbohydrate %kcal
	 * fiber
	 * sugar
	 * Total fat
	 * Saturated fat
	 * Linoleic acid
	 * Linolenic acid
	 * Calcium
	 * Iron
	 * Magnesium
	 * Phosphorus
	 * Potassium
	 * Sodium
	 * Zinc
	 * Copper
	 * Manganese
	 * Selenium
	 * Vitamin A
	 * Vitamin E
	 * Vitamin D
	 * Vitamin C
	 * Thiamin
	 * Riboflavin
	 * Niacin
	 * Vitamin B6
	 * Vitamin B12
	 * Choline
	 * Vitamin K
	 * Folate
	 * @param age
	 * @param gender
	 * @param nutrient
	 * @return
	 */
	public String getNutrientRequirement(int age, String gender, String nutrient) {
		String reccomendation = "Nutrient: " + nutrient + " not found";
		nutrient = nutrient.toLowerCase();
		gender = gender.toLowerCase();
		int nutrientRow = getNutrientRow(nutrient);
		int nutrientColumn = getColumnFromAge(age, gender);
		if (nutrientRow == -1) { return reccomendation; }
		if (gender.contains("fe")) {
			reccomendation = nutritionDataFemale[nutrientRow][nutrientColumn];
		} else {
			reccomendation = nutritionDataMale[nutrientRow][nutrientColumn];
		}		
		return reccomendation;
	}
	
	/** Gets the source of the guidelines for a given nutrient.
	 * RDA = Recommended Dietary Allowance, 
	 * AI = Adequate Intake, 
	 * UL = Tolerable Upper Intake Level, 
	 * AMDR = Acceptable Macronutrient Distribution Range, 
	 * DGA = 2015-2020 Dietary Guidelines recommended limit; 
	 * 14 g fiber per 1,000 kcal = basis for AI for fiber.
	 * 
	 * Nutrient names are case insensitive. Information for the following 
	 * nutrients are available:
	 * 
	 * Protein g
	 * Protein %kcal
	 * Carbohydrate g
	 * Carbohydrate %kcal
	 * fiber
	 * sugar
	 * Total fat
	 * Saturated fat
	 * Linoleic acid
	 * Linolenic acid
	 * Calcium
	 * Iron
	 * Magnesium
	 * Phosphorus
	 * Potassium
	 * Sodium
	 * Zinc
	 * Copper
	 * Manganese
	 * Selenium
	 * Vitamin A
	 * Vitamin E
	 * Vitamin D
	 * Vitamin C
	 * Thiamin
	 * Riboflavin
	 * Niacin
	 * Vitamin B6
	 * Vitamin B12
	 * Choline
	 * Vitamin K
	 * Folate
	 * @param nutrient
	 * @return
	 */
	public String getSource(String nutrient) {
		String source = "Nutrient: " + nutrient + " not found";
		nutrient = nutrient.toLowerCase();
		int nutrientRow = getNutrientRow(nutrient);
		if (nutrientRow != -1) {
			source = nutrientNames[nutrientRow][1];
		}
		return source;
	}
	
	public String getNutrientUnit(String nutrient) {
		String unit = "Nutrient: " + nutrient + " not found";
		
		return unit;
	}
	
	/**
	 * Puts the data parsed from file into age charts for male and female.
	 */
	public void populateAgeArrays() {
		int maleIndex = 0;
		int femaleIndex = 0;
		for (int i = 0; i < 52; i++) {
			if (i == 4) {
				maleIndex = 2;
				femaleIndex = 1;
			}
			if (i == 9) {
				maleIndex = 4;
				femaleIndex = 3;
			}
			if (i == 14) {
				maleIndex = 6;
				femaleIndex = 5;
			}
			if (i == 19) {
				maleIndex = 8;
				femaleIndex = 7;
			}
			if (i == 31) {
				maleIndex = 10;
				femaleIndex = 9;
			}
			if (i == 51) {
				maleIndex = 12;
				femaleIndex = 11;
			}
			ageChartMale[i] = maleIndex;
			ageChartFemale[i] = femaleIndex;
		}
	}
		
	/**
	 * The data chart has ages grouped together, 1-3, 4-8, etc. This function parse the 
	 * age and gender then returns the correct column for the nutrition data.
	 * @param age
	 * @param gender
	 * @return
	 */
	public int getColumnFromAge(int age, String gender) {
		gender = gender.toLowerCase();
		if (age > 51) { age = 51; }
		if (age < 0) { age = 0; }
		int column = 0;
        if (gender.contains("fe")) {
        	column = ageChartFemale[age];
        } else {
        	column = ageChartMale[age];
        }
		return column;
	}

	/**
	 * Match a given nurtient name to the names provided in the data file.
	 * All input is converted to lower case and some partial sub string
	 * matching is supported as long as it is a unique sub sub string in 
	 * the list of nutrient names. 
	 * @param nutrient
	 * @return
	 */
	public int getNutrientRow(String nutrient) {
		nutrient = nutrient.toLowerCase();
		int row = -1;
		for (int i = 0; i < nutrientNames.length; i++) {
			String rowData = nutrientNames[i][0];
			rowData = rowData.toLowerCase();
			if (rowData.contains(nutrient)) {
				row = i;
				return row;
			}
		}
		return row;
	}
	
	/**
	 * Reads in a CSV data file and parses age, gender and nutrient recommendations.
	 * @param fileName
	 */
	public NutritionFileReader(String fileName) {
		File file = new File(fileName); 
		
		try {
			Scanner s = new Scanner(file);
			s.nextLine(); //Skip header line
			int i = 0;
			while (s.hasNextLine()) {
				String dataRow = s.nextLine();
				String[] rowdata = dataRow.split(",");
                if (dataRow.contains("ofgoal")) { 
                	populateAgeArrays();
                }
                else {
                	// Parse Macronutrients names and source to Array
                	nutrientNames[i][0] = rowdata[0].replace("\"", ""); // Name of nutirent
                	// Some elements contain commas and need a different deliminator.
                	int startIndex = 2;
                	nutrientNames[i][1] = rowdata[1]; // Source field
                	for (int j = startIndex; j < rowdata.length; j ++) {
                		nutritionDataMale[i][j - startIndex] = rowdata[j].replace("\"", "");
                		nutritionDataFemale[i][j - startIndex] = rowdata[j].replace("\"", "");
                	}
                	i++;
                }	
			}
			s.close();	
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		}	
	}

}
