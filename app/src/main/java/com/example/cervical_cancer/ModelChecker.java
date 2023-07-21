package com.example.cervical_cancer;

import org.tensorflow.lite.Interpreter;

import java.io.File;

public class ModelChecker {

    public static boolean isModelValid(String modelFilePath) {
        try {
            Interpreter.Options options = new Interpreter.Options();
            Interpreter interpreter = new Interpreter(new File(modelFilePath), options);
            // If the interpreter is successfully created, the model is considered valid
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            // If an exception occurs, the model is likely invalid
            return false;
        }
    }

    public static void main(String[] args) {
        String modelFilePath = "app/src/main/assets/cancer_model.tflite";
//        app/src/main/assets/cancer_model.tflite
        boolean isValid = isModelValid(modelFilePath);
        System.out.println("Is the model valid? " + isValid);
    }
}
