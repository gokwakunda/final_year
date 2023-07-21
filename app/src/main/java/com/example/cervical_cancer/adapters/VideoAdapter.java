package com.example.cervical_cancer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cervical_cancer.R;
import com.example.cervical_cancer.modals.Video;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {

    private List<Video> videos;

    public VideoAdapter(List<Video> videos) {
        this.videos = videos;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_video, parent, false);
        return new VideoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        Video video = videos.get(position);

        // Set video title and description
        holder.textViewTitle.setText(video.getTitle());
        holder.textViewDescription.setText(video.getDescription());

        // Load video thumbnail using an image loading library (e.g., Picasso, Glide) into ImageView
        // You should use an image loading library here to efficiently load images.
        // For example, using Glide:
        Glide.with(holder.itemView.getContext()).load(video.getThumbnail()).into(holder.imageViewThumbnail);

        // Set video URL in the VideoView
        holder.videoView.setVideoPath("https://i.ytimg.com/vi/9m-EpupYkRg/mqdefault.jpg"+ video.getVideoId());
    }

    @Override
    public int getItemCount() {
        return videos.size();
    }

    public static class VideoViewHolder extends RecyclerView.ViewHolder {
        VideoView videoView;
        ImageView imageViewThumbnail;
        TextView textViewTitle;
        TextView textViewDescription;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            videoView = itemView.findViewById(R.id.videoView);
            imageViewThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
        }
    }
}


//package com.example.cervical_cancer.adapters;
//
////public class VideoAdapter {
////}
//
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cervical_cancer.R;
//import com.example.cervical_cancer.modals.Video;
//import com.squareup.picasso.Picasso;
//
//import java.util.List;
//
//public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
//
//    private List<Video> videoList;
//
//    public VideoAdapter(List<Video> videoList) {
//        this.videoList = videoList;
//    }
//
//    @NonNull
//    @Override
//    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View itemView = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_video, parent, false);
//        return new VideoViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
//        Video video = videoList.get(position);
//
//        // Bind data to views
//        holder.textViewTitle.setText(video.getTitle());
//        holder.textViewDescription.setText(video.getDescription());
//        Picasso.get().load(video.getThumbnail()).into(holder.imageViewThumbnail);
//
//        // Set video URI for the VideoView
//        Uri videoUri = Uri.parse(" https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&q=cervical+cancer&maxResults=10&key=AIzaSyAmCo1JBMIdcRL2SiWigauaHSlRp3HFMjg'/" + video.getVideoId());
//        holder.videoView.setVideoURI(videoUri);
//
//        // Optionally, you can handle video playback controls
//        // E.g., starting the video when the user clicks on the item
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                holder.videoView.start();
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return videoList.size();
//    }
//
//    public static class VideoViewHolder extends RecyclerView.ViewHolder {
//        VideoView videoView;
//        ImageView imageViewThumbnail;
//        TextView textViewTitle, textViewDescription;
//
//        public VideoViewHolder(@NonNull View itemView) {
//            super(itemView);
//            videoView = itemView.findViewById(R.id.videoView);
//            imageViewThumbnail = itemView.findViewById(R.id.imageViewThumbnail);
//            textViewTitle = itemView.findViewById(R.id.textViewTitle);
//            textViewDescription = itemView.findViewById(R.id.textViewDescription);
//        }
//    }
//}
