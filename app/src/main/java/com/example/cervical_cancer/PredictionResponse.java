package com.example.cervical_cancer;

import com.google.gson.annotations.SerializedName;

public class PredictionResponse {
    @SerializedName("prediction")
    private int prediction;

    public int getPrediction() {
        return prediction;
    }
}

//
//public class PredictionResponse {
//    private int prediction;
//
//    public int getPrediction() {
//        return prediction;
//    }
//
//    public void setPrediction(int prediction) {
//        this.prediction = prediction;
//    }
//}
//
////
//import com.example.cervical_cancer.modals.Video;
//import com.google.gson.annotations.SerializedName;
//
//import java.util.List;
//
//public class ApiResponse {
//    @SerializedName("items")
//    private List<Video> videos;
//
//    public List<Video> getVideos() {
//        return videos;
//    }
//
//    public void setVideos(List<Video> videos) {
//        this.videos = videos;
//    }
//}
