package com.example.cervical_cancer.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cervical_cancer.R;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class YouTubePlayerAdapter extends RecyclerView.Adapter<YouTubePlayerAdapter.ViewHolder> {

    private String[] videoIds;

    public YouTubePlayerAdapter(String[] videoIds) {
        this.videoIds = videoIds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_player, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String videoId = videoIds[position];
        holder.bind(videoId);
    }

    @Override
    public int getItemCount() {
        return videoIds.length;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private YouTubePlayerView youTubePlayerView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            youTubePlayerView = itemView.findViewById(R.id.youtube_player_view_item);
        }

        void bind(String videoId) {
            youTubePlayerView.getYouTubePlayerWhenReady(new YouTubePlayerCallback() {
                @Override
                public void onYouTubePlayer(YouTubePlayer youTubePlayer) {
                    youTubePlayer.cueVideo(videoId, 0);
                }
            });
        }
    }
}



//package com.example.cervical_cancer.adapters;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cervical_cancer.R;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
//
//public class YouTubePlayerAdapter extends RecyclerView.Adapter<YouTubePlayerAdapter.ViewHolder> {
//
//    private String[] videoIds;
//
//    public YouTubePlayerAdapter(String[] videoIds) {
//        this.videoIds = videoIds;
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_youtube_player, parent, false);
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder.bind(videoIds[position]);
//    }
//
//    @Override
//    public int getItemCount() {
//        return videoIds.length;
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder {
//        private YouTubePlayerView youtubePlayerView;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            youtubePlayerView = itemView.findViewById(R.id.youtube_player_view_item);
//        }
//
//        public void bind(String videoId) {
//            youtubePlayerView.getYouTubePlayerWhenReady(youTubePlayer -> {
//                youTubePlayer.loadVideo(videoId, 0);
//            });
//        }
//    }
//}
