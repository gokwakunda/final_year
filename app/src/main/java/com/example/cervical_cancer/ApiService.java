package com.example.cervical_cancer;

import com.example.cervical_cancer.modals.InputData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("/predict")
    Call<PredictionResponse> predictCancer(@Body InputData inputData);

}


//
//import com.example.cervical_cancer.modals.InputData;
//
//import retrofit2.Call;
//import retrofit2.http.Body;
//import retrofit2.http.POST;
//
//public interface ApiService {
//    @POST("/predict")
//    Call<PredictionResponse> predictCancer(@Body InputData inputData);
//}




//package com.example.cervical_cancer;
//
//import retrofit2.Call;
//import retrofit2.http.GET;
//import retrofit2.http.Query;
//
//public interface ApiService {
//    @GET("search?type=video")
//    Call<ApiResponse> getCervicalCancerVideos(
//            @Query("part") String part,
//            @Query("q") String searchQuery,
//            @Query("maxResults") int maxResults,
//            @Query("key") String apiKey
//    );
//}
//
