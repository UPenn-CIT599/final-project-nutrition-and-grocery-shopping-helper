package com.microsoft.azure.customvision.samples;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.*;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.*;
import com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.ImagePrediction;

import com.microsoft.azure.cognitiveservices.vision.customvision.training.*;
import com.microsoft.azure.cognitiveservices.vision.customvision.training.models.*;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections; 



public class GroceryImageClassification {

	public static String trainingKey = "d439052477fe42e2ba97ed5e2fef84a9"; //Training key from Custom Vision account
    private static final String basePath = "data"; //Put in the path to your training data set 
    
    private static List<File> bananaImages; 
    private static List<File> appleImages; 
    
    
    private static byte[] testImage; //We are seeking a prediction tag for this image
    
    public static void main(String[] args) 
   
    {

    	//Authenticate Train API w/ training key
    	TrainingApi client = CustomVisionTrainingManager.authenticate(trainingKey);
    	
    	// Create a new project in the Custom Vision Account 
    	System.out.println("Creating new project:"); 
    	Trainings trainings = client.trainings();
    	Project project = trainings.createProject().withName("GroceryClassifierMCIT1").execute();
    	
    	
    	//create classification Tags
    	System.out.println("Create Tags"); 
    	Tag BananaTag = trainings.createTag().withProjectId(project.id()).withName("Banana").execute();
    	Tag AppleTag = trainings.createTag().withProjectId(project.id()).withName("Apple").execute();
    			
    	//Add some images to the tags 
    	System.out.println("Uploading images to tags"); 
    	GroceryImageClassification.LoadImagesFromDirectory();
    	
    	
    	//Upload images one at a time to Tag
    	for(File image : bananaImages)
    	{
    		List<String> BananasTagIds = new ArrayList<String>();
    		BananasTagIds.add(BananaTag.id().toString());
    		
    		try {
    			
        		byte[] fileContent = Files.readAllBytes(image.toPath());
        		trainings.createImagesFromData()
        		.withProjectId(project.id())
        		.withImageData(fileContent)
        		.withTagIds(BananasTagIds )
        		.execute();
        		
    		} catch (Exception e) {
    			
    			System.out.println("Exception occurred: " + e );
    		}
   	    
    	}
    	
    	for(File img : appleImages)
    	{
    		List<String> AppleTagIds = new ArrayList<String>();
    		AppleTagIds.add(AppleTag.id().toString());
    		
    		try {
    			
        		byte[] content = Files.readAllBytes(img.toPath());
        		trainings.createImagesFromData()
        		.withProjectId(project.id())
        		.withImageData(content)
        		.withTagIds(AppleTagIds )
        		.execute();
        		
    		} catch (Exception e) {
    			
    			System.out.println("Exception occurred: " + e );
    		}
   	    
    	}
    	
        // Now there are images with tags start training the project 
        System.out.println("Training"); 
        Iteration iteration = trainings.trainProject(project.id());

        // The returned iteration will be in progress, and can be queried periodically to see when it has completed 
        while (iteration.status().equals("Training") )
        { 
            try {
            	
				Thread.sleep(1000);
				
	            // Re-query the iteration to get it's updated status 
	            iteration = trainings.getIteration(project.id(), iteration.id());
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			} 

            		 
        } 

         // The iteration is now trained. Make it the default project endpoint 
        // iteration.withIsDefault(true);
         //System.out.println("Done!\n"); 

    	
    	/*********************************************************************/
        /* 				PREDICTION						                     */
        /*********************************************************************/
         
		 // Now there is a trained endpoint, it can be used to make a prediction 
         

         // Add your prediction key from the settings page of the portal 
         // The prediction key is used in place of the training key when making predictions 
     	final String predictionKey = "4ee4be6d615247f38fa185b4342c1a1d";

         // Create a prediction endpoint, passing in obtained prediction key 
     	PredictionEndpoint endpoint = CustomVisionPredictionManager.authenticate(predictionKey);

     	// Make a prediction against the new project 
     	System.out.println("Making a prediction..."); 
     	ImagePrediction result = endpoint.predictions().predictImage().withProjectId(project.id()).withImageData(testImage).withIterationId(iteration.id()).execute();
     	
     	double prob = 0.0;
		String tag = null; 
     	for(com.microsoft.azure.cognitiveservices.vision.customvision.prediction.models.Prediction c : result.predictions()) 
     		{ 
   
     			if (c.probability() > prob) { 
     				prob  = c.probability(); 
     				tag = c.tagName(); 
     			}  		    
     		    
     		}
     	System.out.println("The predicted tag of the uploaded image is " + tag); 
     	
  
   }
  

    public static void LoadImagesFromDirectory() 
    { 
    	// this loads the images to be uploaded from Local directory
    	  File fileA = new File("data/bananas");
    	  if (fileA.isDirectory()) 
    	  {
    		 File [] fileList = fileA.listFiles();
    		  bananaImages = Arrays.asList(fileList);
    		  
    	  }
    	  
    	  //this loads the images to be uploaded from Local directory
    	  File fileB = new File("data/apples");
    	  if (fileB.isDirectory()) 
    	  {
    		  File [] files = fileB.listFiles();
    		  appleImages = Arrays.asList(files);

    	  }
    	  
    	  //this loads an image to test against the trained model
    	  File testFile = new File("test.jpeg");
    	  try {
    		  
			testImage = Files.readAllBytes(testFile.toPath());
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    } 

	
	
	
}