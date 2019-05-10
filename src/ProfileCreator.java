import java.util.Scanner;

/**
 * Asks user a series of questions to create an instance of Person
 * holding their age, gender, activity level
 */
public class ProfileCreator {
    private Scanner scanner = new Scanner(System.in);

    public Person createProfile() {
        System.out.println("Hello, I would like to ask some questions to build your nutrition profile.");
        String gender = askGender();
        int age = askAge();
        String activityLevel = askActivityLevel();
        return new Person(age, gender, activityLevel);
    }

    private int askAge() {
        System.out.println("What is your current age?");
        int age = Integer.parseInt(scanner.nextLine());
        if (age < 1 || age > 130) {
            System.out.println("Please enter a valid age!");
            return askAge();
        } else {
            return age;
        }
    }

    private String askGender() {
        System.out.println("What gender do you identify with?");
        System.out.println("Press 1 for male");
        System.out.println("Press 2 for female");
        String input = scanner.nextLine();
        if (input.equals("1")) {
            return "male";
        } else if (input.equals("2")) {
            return "female";
        } else {
            System.out.println("Sorry, that input was not valid. Please try again.");
            return askGender();
        }
    }

    private String askActivityLevel() {
        System.out.println("What is your activity level?");
        System.out.println("Press 1 for \"Sedentary\"");
        System.out.println("Press 2 for \"Moderate\"");
        System.out.println("Press 3 for \"Active\"");
        String level = scanner.nextLine();
        if (level.equals("1")) {
            return "Sedentary";
        } else if (level.equals("2")) {
            return "Moderate";
        } else if (level.equals("3")) {
            return "Active";
        } else {
            System.out.println("You need to choose one of the 3 options!");
            return askActivityLevel();
        }
    }
}
