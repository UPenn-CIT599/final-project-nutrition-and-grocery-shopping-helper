
public class Person {
	
	int age;
	String gender;
	String activityLevel;
	
	public Person(int currAge, String currGender, String currActivityLevel) {
		age = currAge;
		gender = currGender;
		activityLevel = currActivityLevel;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * @return the activityLevel
	 */
	public String getActivityLevel() {
		return activityLevel;
	}

	/**
	 * @param activityLevel the activityLevel to set
	 */
	public void setActivityLevel(String activityLevel) {
		this.activityLevel = activityLevel;
	}

}
