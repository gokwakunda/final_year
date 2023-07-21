package com.example.cervical_cancer;

import android.content.Context;

import org.tensorflow.lite.Interpreter;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.io.File;

public class ModelLoader {
    private Interpreter interpreter;

    public ModelLoader(Context context) {
        try {
            // Load the model from the assets directory
            MappedByteBuffer modelFile = loadModelFile();

            // Create the TensorFlow Lite interpreter
            interpreter = new Interpreter(modelFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private MappedByteBuffer loadModelFile() throws IOException {
        File file = new File("app/src/main/assets/cancer_model.tflite");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel = fileInputStream.getChannel();
        long startOffset = fileChannel.position();
        long declaredLength = fileChannel.size();
        return fileChannel.map(FileChannel.MapMode.READ_ONLY, startOffset, declaredLength);
    }

    public int predictRisk(int[] inputFeatures) {
        // Preprocess the input features if necessary
        float[] normalizedInput = preprocessInput(inputFeatures);

        // Create input and output arrays
        float[][] inputArray = new float[1][normalizedInput.length];
        float[][] outputArray = new float[1][1];

        // Set the input array
        inputArray[0] = normalizedInput;

        // Run the model
        interpreter.run(inputArray, outputArray);

        // Get the prediction
        float prediction = outputArray[0][0];

        // Postprocess the prediction if necessary
        int risk = postprocessPrediction(prediction);

        return risk;
    }

    private float[] preprocessInput(int[] inputFeatures) {
        // Normalize input features to a suitable range (e.g., 0 to 1)
        float[] normalizedInput = new float[inputFeatures.length];
        // Apply normalization logic

        return normalizedInput;
    }

    private int postprocessPrediction(float prediction) {
        // Convert the prediction to a risk class (0 or 1)
        int risk = (prediction >= 0.5f) ? 1 : 0;

        return risk;
    }
}
