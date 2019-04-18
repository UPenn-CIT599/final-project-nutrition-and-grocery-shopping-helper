import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CalorieFileReader {
	
	/*  
	[0] = ageRangeArray;
	[1] = caloriesSedentaryMale;
	[2] = caloriesModerateMale;
	[3] = caloriesActiveMale;
	[4] = caloriesSedentaryFemale;
	[5] = caloriesModerateFemale;
	[6] = caloriesActiveFemale; */
	private int[][] calorieData;
	
	/**
	 * Removes commas and double quotes from a string and returns its parsed int value.
	 * @param element
	 * @return
	 */
	public int removePunctuation(String element) {
		String temp = element.replace("\"", "");
		temp = temp.replace(",", "");
		return Integer.parseInt(temp);		
	}
	
	/**
	 * Removes the "-" from ages and returns an array of the age range.
	 * @param ageAsString
	 * @return
	 */
	public int[] ageParser(String ageAsString) {
		int arraySize = 1; 
		int min; 
		if (ageAsString.contains("-")) {
			String[] minMaxString = ageAsString.split("-");
			
			min = Integer.parseInt(minMaxString[0]);
			int max = Integer.parseInt(minMaxString[1]);
			arraySize = max - min + 1;
			
		}
		else if (ageAsString.contains("76 and up")){
			min = 76;
		}
		else {
			min = Integer.parseInt(ageAsString);
		}
		int[] ageAsInt = new int[arraySize];
		for (int i = 0; i < arraySize; i++) {
			ageAsInt[i] = min + i;
		}		
		return ageAsInt;		
	}
	
	public int getCalories(int age, String gender, String activityLevel) {
		if (age > 76) { age = 76; }
		if (age < 2) { age = 2; }
		int calories = -1;
		int activityLevelInt = 0;
		if (activityLevel.contentEquals("Sedentary")) { activityLevelInt += 1; }
		if (activityLevel.contentEquals("Moderate")) { activityLevelInt += 2; }
		if (activityLevel.contentEquals("Active")) { activityLevelInt += 3; }
		if (gender.contentEquals("Female")) { activityLevelInt += 3; }
		calories = calorieData[age - 2][activityLevelInt];
		return calories;
	}
	
	/**
	 * Constructor that reads the calories form a file and populates a private var with them.
	 * @param fileName
	 */
	public CalorieFileReader(String fileName) {
		File file = new File(fileName);
		calorieData = new int[75][7];
		
		try {
			Scanner s = new Scanner(file);
			s.nextLine(); //Skip header line
			s.nextLine(); //Skip header line 2
			s.nextLine(); //Skip header line 3
			while (s.hasNextLine()) {
				String dataRow = s.nextLine();
				String[] rowdata = dataRow.split(",\"");

				String ageRange = rowdata[0];
				int[] ageRangeArray = ageParser(ageRange);
				// Male
				int caloriesSedentaryMale = removePunctuation(rowdata[1]);
				int caloriesModerateMale = removePunctuation(rowdata[2]);
				int caloriesActiveMale = removePunctuation(rowdata[3]);
				//Female
				int caloriesSedentaryFemale = removePunctuation(rowdata[4]);
				int caloriesModerateFemale = removePunctuation(rowdata[5]);
				int caloriesActiveFemale = removePunctuation(rowdata[6]);
				
				for (int i = 0; i < ageRangeArray.length; i++) {
					calorieData[ageRangeArray[i] - 2][0] = ageRangeArray[i];
					calorieData[ageRangeArray[i] - 2][1] = caloriesSedentaryMale;
					calorieData[ageRangeArray[i] - 2][2] = caloriesModerateMale;
					calorieData[ageRangeArray[i] - 2][3] = caloriesActiveMale;
					calorieData[ageRangeArray[i] - 2][4] = caloriesSedentaryFemale;
					calorieData[ageRangeArray[i] - 2][5] = caloriesModerateFemale;
					calorieData[ageRangeArray[i] - 2][6] = caloriesActiveFemale;
				}				
			}
			s.close();	
		} catch (FileNotFoundException e) {		
			e.printStackTrace();
		}
		
		
		
	}

}
