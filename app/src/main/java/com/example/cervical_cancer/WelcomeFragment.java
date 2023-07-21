package com.example.cervical_cancer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class WelcomeFragment extends Fragment {
    private VideoView videoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get the VideoView from the layout
//        videoView = view.findViewById(R.id.videoView);
//
//        // Set the Cloudinary video URL to the VideoView
//        String videoUrl = "https://res.cloudinary.com/dhvdgtaoh/video/upload/v1686907912/RISK_FACTORS_FOR_CERVICAL_CANCER_dka7pf.mp4";
//        videoView.setVideoURI(Uri.parse(videoUrl));
//        videoView.start();
    }
}








//package com.example.cervical_cancer;
//
//import android.net.Uri;
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.MediaController;
//import android.widget.VideoView;
//
//import androidx.fragment.app.Fragment;
//
//import com.cloudinary.android.MediaManager;
//import com.cloudinary.android.callback.ErrorInfo;
//import com.cloudinary.android.callback.UploadCallback;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class WelcomeFragment extends Fragment {
//    private VideoView videoView1;
//    private VideoView videoView2;
//    private VideoView videoView3;
//    private VideoView videoView4;
//    private VideoView videoView5;
//    private View rootView;
//    private MediaController mediaController1;
//    private MediaController mediaController2;
//    private MediaController mediaController3;
//    private MediaController mediaController4;
//
//    public WelcomeFragment() {
//    }
////    public WelcomeFragment() {
////        // Required empty public constructor
////    }
////    import com.cloudinary.Cloudinary;
////import com.cloudinary.android.MediaManager;
//
//    public static WelcomeFragment newInstance() {
//        return new WelcomeFragment();
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
//
//            // Initialize Cloudinary
//            MediaManager.init(getActivity());
//
//            // Other code for fragment initialization
//
//        videoView1 = rootView.findViewById(R.id.videoView1);
//        videoView2 = rootView.findViewById(R.id.videoView2);
//        videoView3 = rootView.findViewById(R.id.videoView3);
//        videoView4 = rootView.findViewById(R.id.videoView4);
//
//        mediaController1 = new MediaController(getActivity());
//        mediaController2 = new MediaController(getActivity());
//        mediaController3 = new MediaController(getActivity());
//        mediaController4 = new MediaController(getActivity());
//
//        String videoUrl1 = "android.resource://" + getActivity().getPackageName() + "/raw/video1";
//        String videoUrl2 = "android.resource://" + getActivity().getPackageName() + "/raw/video2";
//        String videoUrl3 = "android.resource://" + getActivity().getPackageName() + "/raw/video3";
//        String videoUrl4 = "android.resource://" + getActivity().getPackageName() + "/raw/video4";
//
//        videoView1.setVideoURI(Uri.parse(videoUrl1));
//        videoView2.setVideoURI(Uri.parse(videoUrl2));
//        videoView3.setVideoURI(Uri.parse(videoUrl3));
//        videoView4.setVideoURI(Uri.parse(videoUrl4));
//
//        videoView1.setMediaController(mediaController1);
//        videoView2.setMediaController(mediaController2);
//        videoView3.setMediaController(mediaController3);
//        videoView4.setMediaController(mediaController4);
//
//
//        mediaController1.setAnchorView(videoView1);
//        mediaController2.setAnchorView(videoView2);
//        mediaController3.setAnchorView(videoView3);
//        mediaController4.setAnchorView(videoView4);
//
//        return rootView;
//    }
//    public void uploadVideoFromLink(String videoLink) {
//        // Define upload parameters
//        Map<String, Object> params = new HashMap<>();
//        params.put("public_id", "my_video_id");
//        params.put("folder", "my_folder");
//
//        // Upload the video from the link
//        MediaManager.get().upload(videoLink)
//                .option("tags", "my_video_tag")
//                .option("resource_type", "video")
//                .callback(new UploadCallback() {
//                    @Override
//                    public void onStart(String requestId) {
//                        // Called when the upload starts
//                    }
//
//                    @Override
//                    public void onProgress(String requestId, long bytes, long totalBytes) {
//                        // Called during the upload progress
//                    }
//
//                    @Override
//                    public void onSuccess(String requestId, Map resultData) {
//                        // Called when the upload is successful
//                        String videoUrl = (String) resultData.get("url");
//                        // Use the uploaded video URL as needed
//                    }
//
//                    @Override
//                    public void onError(String requestId, ErrorInfo error) {
//                        // Called when an error occurs during upload
//                    }
//
//                    @Override
//                    public void onReschedule(String requestId, ErrorInfo error) {
//                        // Called when a reschedule is required
//                    }
//                })
//                .dispatch();
//    }
//
//
//
//}
;
//
////        Button linkButton = rootView.findViewById(R.id.linkButton);
////        linkButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
//////                    String url = "https://www.example.com";
////                String url = "https://www.cancer.org/cancer/cervical-cancer.html";
////                Intent intent = new Intent(Intent.ACTION_VIEW);
////                intent.setData(Uri.parse(url));
////                startActivity(intent);
////            }
////        });
//
//
//        return rootView;
//
//    }
//}
