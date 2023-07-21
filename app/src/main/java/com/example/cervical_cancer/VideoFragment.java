//
//package com.example.cervical_cancer;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Toast;
//
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cervical_cancer.adapters.VideoAdapter;
//import com.example.cervical_cancer.modals.Video;
//
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class VideoFragment extends Fragment {
//
//    private RecyclerView recyclerView;
//    private VideoAdapter videoAdapter;
//
//    @SuppressLint("MissingInflatedId")
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_video, container, false);
//
//        recyclerView = rootView.findViewById(R.id.recyclerViewVideos);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        // Fetch videos from your backend API and pass the list to the adapter
//        fetchVideos();
//
//        return rootView;
//    }
//
//    private void fetchVideos() {
//        ApiService apiService = RetrofitClient.getClient().create(ApiService.class);
//
//        String part = "snippet"; // The part of the video resource that you want to retrieve
//        String searchQuery = "cervical cancer"; // The keyword you want to search for
//        int maxResults = 10; // The maximum number of results you want to fetch
//        String apiKey = "AIzaSyAmCo1JBMIdcRL2SiWigauaHSlRp3HFMjg"; // Your actual API key from Google
//
//        Call<ApiResponse> call = apiService.getCervicalCancerVideos(part, searchQuery, maxResults, apiKey);
//
//        call.enqueue(new Callback<ApiResponse>() {
//            @Override
//            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
//                if (response.isSuccessful()) {
//                    ApiResponse apiResponse = response.body();
//                    List<Video> videos = apiResponse.getVideos();
//                    displayVideos(videos);
//                } else {
//                    // Handle API error
//                    Toast.makeText(getContext(), "Failed to fetch videos.", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ApiResponse> call, Throwable t) {
//                // Handle network failure
//                Toast.makeText(getContext(), "Network error occurred.", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    private void displayVideos(List<Video> videos) {
//        // Pass the list of videos to the adapter
//        videoAdapter = new VideoAdapter(videos);
//        recyclerView.setAdapter(videoAdapter);
//    }
//}
//
