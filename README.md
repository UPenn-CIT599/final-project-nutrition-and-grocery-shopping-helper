# MCIT 591 Final Project: Computer Vision Nutrition Helper

## Team Members
Sid Sathi
Richard Zorger
Jonathan Deng

## Project Description

Our group would like to create an application that allows people to take pictures of food and see how this food would affect their progress towards nutrition and diet goals.
Using a pre-trained image classifier, the USDA REST API for nutrition information, and files on nutrition guidelines provided by the USDA, we can make a prediction what food item the user took a picture of, and show how eating the food would affect their health goals.

## References
[Initial Project Proposal](projectProposal.md)

[Presentation Slide Deck](https://docs.google.com/presentation/d/1Yb9poVkx75O-QNyQgVJNzV6YN-1qakat1r1r0t8jX3I/edit)

## User Flow
1. User uploads an image to the `/data` directory of this project. (In a mobile application, the client would have access to the camera API and handle this)

2. A training image set is uploaded to the Microsoft Azure Custom Vision API, where a pre-existing classifier is trained on tagged food images. (In a production application this training step would occur asynchronously prior to the user uploading their photo)

3. The user uploaded image is classified by the model running on Azure, which returns a String of the food name, i.e. "banana" or "apple"

4. The search string is use to query the USDA Food Database REST API for nutrition details about the food.

5. The user enters nutrition and health goals and information about their age, sex, and activity level. This allows the program to build a user
profile.

6. The program references USDA guidelines (stored in csv files in this project) to look up specific requirements based on the user profile

7. The program compares nutrition information describing the uploaded food image and compares it to personalized guidelines to inform a user how eating 
that food affects them.

## Running this project

### 1. Build
This project uses Maven as a build tool to import dependency libraries.  It needs to be imported into the IDE as a Maven project to delegate build and run tasks to Maven.

### 2. Configure
This project depends on 2 external APIs, which require keys:
1. Microsoft Custom Vision: key is hardcoded in class representing the API access (don't worry it's a free account level test key!)
2. USDA Database API: key needs to be pasted in `apiKey.txt` in the root level. (Delete `"DEMO_KEY"` string in that file.)

To get a USDA API Key go to [this page](https://ndb.nal.usda.gov/ndb/doc/index#) and click "Sign Up Now".

### 3. Run
The main class that orchestrates the entire user flow is `NutritionHelper.java`.

Run this project by running `NutritionHelper.main`

### 4. Test

Unit tests for this project are contained in the `test` directory

## Troubleshooting

### Issue: Microsoft API returns 400
Sometimes the Microsoft Custom Vision API refuses to create a project with the same name as one previously. Change [this String to something that you haven't used before to fix this problem](https://github.com/UPenn-CIT599/final-project-nutrition-and-grocery-shopping-helper/blob/master/src/GroceryImageClassification.java#L43)