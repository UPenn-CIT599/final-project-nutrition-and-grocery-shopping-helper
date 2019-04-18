# final-project-nutrition-and-grocery-shopping-helper

Nutrition and Grocery Shopping Helper
Computer Vision Based Grocery Basket Analysis

Team Members

Sid Sathi
Richard Zorger
Jonathan Deng

Project Idea and Description

Our group would like to create an application that allows people to take pictures of food and see how this food would affect their progress towards nutrition and diet goals.  Using a pre-trained image classifier, we can make a prediction what food item the user took a picture of, and show how eating the food would affect their health goals.

Create a program where:
•	The user enters nutrition and health goals and information about their age, sex, activity level, etc.
•	The user uploads an image file and using a pre-trained classification model, the program will output what food item the user uploaded.
•	The user sees how eating this food item would affect their nutrition goals. Nutrition information would be accessed via an API (i.e. MyFitnessPal API) or within some files contained in the project.

Task Breakdown

Sid: How do we use a ML model to classify an image a user uploads? 

Use the DeepLearning4j Deep Learning library to build an image classifier. Build pipelines to make predictions for food items. 

Jon: Once we know what food item a user uploaded, how do we access its nutrition information (API or local files)

Create a FoodProfileRepository class that accesses a food’s nutrition information given its name (this class will abstract whether data is accessed locally or over the network)

Create APIRequest class to get nutrition details from the API

Richard: Once we have a food’s nutrition information, how do we show its significance in relation to the user’s nutrition goals.

Possible sources of personal nutritional guidelines:
https://health.gov/dietaryguidelines/2015/guidelines/appendix-7/
https://health.gov/dietaryguidelines/2015/guidelines/appendix-2/

Create a Person class with instance variables for age, sex, activity level, and personal goals.

Create getter functions for Macronutrients, Minerals, Vitamins and Calories accounting for instance variable customization.

