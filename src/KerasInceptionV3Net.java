import org.apache.commons.io.IOUtils;
import org.datavec.image.loader.NativeImageLoader;
import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.modelimport.keras.InvalidKerasConfigurationException;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;
import org.deeplearning4j.nn.modelimport.keras.UnsupportedKerasConfigurationException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.function.Function;

/**
 * 
 */

public class KerasInceptionV3Net {

    private static final Logger LOG = LoggerFactory.getLogger(KerasInceptionV3Net.class);

    //These are specific to model being imported 
    private int imgWidth = 299;
    private int imgHeight = 299;
    private int imgChannels = 3;
    private int numClasses = 1000;
    private ComputationGraph model;

    private double confidenceThreshold = 0.05;
    private NativeImageLoader imageLoader = new NativeImageLoader(imgHeight, imgWidth, imgChannels);
    private final Map<Integer, String> classMap = new HashMap<>();

    // preprocessor for this model
    private Function<INDArray, INDArray> preProcessor = image -> image.div(255.0).sub(0.5).mul(2);

    public KerasInceptionV3Net(String modelJsonPath, String weightsH5Path) throws
            UnsupportedKerasConfigurationException, IOException, InvalidKerasConfigurationException {
        LOG.info("Importing Inception model from {}", modelJsonPath);
        LOG.info("Importing Weights model from {}", weightsH5Path);
        long st = System.currentTimeMillis();
        try {
			model = KerasModelImport.importKerasModelAndWeights(modelJsonPath, weightsH5Path);
		} catch (org.deeplearning4j.nn.modelimport.keras.exceptions.InvalidKerasConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (org.deeplearning4j.nn.modelimport.keras.exceptions.UnsupportedKerasConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
        LOG.info("Time taken to import model {} ms", (System.currentTimeMillis() - st));
    }

    public INDArray preProcessImage(String imagePath) throws IOException {
        try (InputStream imageStream = new FileInputStream(imagePath)){ //Load in each image
            return preProcessImage(imageStream); //Run pre-processing on image
        }
    }

    public INDArray preProcessImage(InputStream imageStream) throws IOException {
        return preProcessor.apply(imageLoader.asMatrix(imageStream));
    }
    
    public ArrayList<Label> classify(INDArray image) throws IOException { 
    	INDArray scores = model.output(image)[0];
        assert scores.length() == numClasses;
        ArrayList<Label> result = new ArrayList<>();
        for (int i = 0; i < scores.length(); i++) {
            if (scores.getDouble(i) >= confidenceThreshold) {
                result.add(new Label(i, scores.getDouble(i), classMap.get(i)));
            }
        }
        return result;
    }
    
    
    

    public void loadClassIndex(InputStream stream) throws IOException {
        String content = IOUtils.toString(stream);
        JSONObject jIndex = new JSONObject(content);
        classMap.clear();
        jIndex.keys().forEach(k -> { //lambda operator 
            String key = k.toString();
            JSONArray names = jIndex.getJSONArray(key);
            classMap.put(Integer.parseInt(key), names.getString(names.length() - 1));
        });
    }

    public static void main(String[] args) throws UnsupportedKerasConfigurationException,
            IOException, InvalidKerasConfigurationException {
        String modelFile = "data/inception-model.json";
        String weightsFile = "data/inception-model-weights.h5";
        String classMapFile = "data/imagenet_class_index.json";
        
        File[] images; //Will be an array of pathnames from our image data
       
        if (args.length > 0){ // there are CLI args
            images = Arrays.stream(args).map(File::new).toArray(File[]::new);
        } else {
            File imagesDir = new File("data/images");
            images = imagesDir.listFiles(pathname -> pathname.getName().endsWith(".jpg"));
        }
        assert images != null;
        LOG.debug("Found {} images", images.length);
        KerasInceptionV3Net net = new KerasInceptionV3Net(modelFile, weightsFile);
        try (InputStream is = new FileInputStream(classMapFile)){
            net.loadClassIndex(is);
        }
        
        
        for (File imageFile: images) {
        	ArrayList<Label> result = new ArrayList<Label>(); 
        	INDArray imgData = net.preProcessImage(imageFile.getPath());
            long st = System.currentTimeMillis();
            ArrayList<Label> results = net.classify(imgData); 
            long timeTaken = System.currentTimeMillis() -  st;
            System.out.println(imageFile + ":: " + timeTaken + "ms" + " ::: " + results);
        }
        System.out.println("Done");
    }
}
