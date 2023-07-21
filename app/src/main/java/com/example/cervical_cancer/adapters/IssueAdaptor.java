package com.example.cervical_cancer.adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cervical_cancer.R;
import com.example.cervical_cancer.modals.IssueModal;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
public class IssueAdaptor extends RecyclerView.Adapter<IssueAdaptor.MyViewHolder> {
    private Context context;
    private ArrayList<IssueModal> list;
    private OnIssueClickListener onIssueClickListener;

    public interface OnIssueClickListener {
        void onIssueClicked(int position);
    }

    public IssueAdaptor(Context context, ArrayList<IssueModal> list, OnIssueClickListener onIssueClickListener) {
        this.context = context;
        this.list = list;
        this.onIssueClickListener = onIssueClickListener;
    }

    public void setList(ArrayList<IssueModal> newList) {
        list = newList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.text_card, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        IssueModal issueModal = list.get(list.size() - 1 - position); // Calculate the actual position
        holder.title.setText(issueModal.getTitle());
        holder.detail.setText(issueModal.getDetail());
        holder.author.setText(issueModal.getAuthor());

        // Load profile image using Glide
        Glide.with(context)
                .load(issueModal.getProfile())
                .error(R.drawable.profile)
                .into(holder.profile);

        String imageUri = issueModal.getImageUri();
        String videoUri = issueModal.getVideoUri();

        // Check if image or video is present
        if (imageUri != null) {
            // Show image using Glide
            Glide.with(context)
                    .load(imageUri)
                    .error(R.drawable.cer)
                    .into(holder.image);
            holder.idimagecard.setVisibility(View.VISIBLE);
            holder.image.setVisibility(View.VISIBLE);
       }
//        else {
//            // Hide image if not present
//            holder.idimagecard.setVisibility(View.GONE);
//        }

        else if (videoUri != null) {
            // Show video using VideoView
            holder.idimagecard.setVisibility(View.VISIBLE);
            holder.videoView.setVisibility(View.VISIBLE);

            // Set video URI and start the video
            holder.videoView.setVideoURI(Uri.parse(videoUri));

            // Remove the media controller from the VideoView to customize its appearance
            holder.videoView.setMediaController(null);
            // Make the media controller visible even when the video view is not clicked
            holder.videoView.setMediaController(new MediaController(context));

        } else {
            // Hide video if not present
            holder.idimagecard.setVisibility(View.GONE);
        }

        // Set click listener for the comment button
        holder.commentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION && onIssueClickListener != null) {
                    onIssueClickListener.onIssueClicked(position);
                }
            }
        });
    }

//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView title, detail, author;
//        ImageView image;
//        VideoView videoView;
//        ImageButton commentButton;
//        CircleImageView profile;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            title = itemView.findViewById(R.id.textCardTitle);
//            detail = itemView.findViewById(R.id.textCardBody);
//            author = itemView.findViewById(R.id.textcard_username);
//            profile = itemView.findViewById(R.id.profile_icon_card);
//            image = itemView.findViewById(R.id.imageView);
//            videoView = itemView.findViewById(R.id.videoView);
//            commentButton = itemView.findViewById(R.id.comm);
//        }
//    }
//}


//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        IssueModal issueModal = list.get(list.size() - 1 - position); // Calculate the actual position
//        holder.title.setText(issueModal.getTitle());
//        holder.detail.setText(issueModal.getDetail());
//        holder.author.setText(issueModal.getAuthor());
//
//        // Load profile image using Glide
//        Glide.with(context)
//                .load(issueModal.getProfile())
//                .error(R.drawable.profile)
//                .into(holder.profile);
//
//        String imageUri = issueModal.getImageUri();
//        String videoUri = issueModal.getVideoUri();
//
//        if (imageUri != null) {
//            // Show image using Glide
//            Glide.with(context)
//                    .load(imageUri)
//                    .error(R.drawable.cer)
//                    .into(holder.image);
//            holder.image.setVisibility(View.VISIBLE);
//        } else {
//            holder.image.setVisibility(View.GONE);
//        }
//
//        if (videoUri != null) {
//            // Show video using VideoView
//            holder.videoView.setVisibility(View.VISIBLE);
//
//            // Set video URI and start the video
//            holder.videoView.setVideoURI(Uri.parse(videoUri));
//
//            // Add media controller to the VideoView
//            MediaController mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.videoView);
//            holder.videoView.setMediaController(mediaController);
//
//            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    // Pause the video initially
//                    holder.videoView.pause();
//                }
//            });
//        } else {
//            holder.videoView.setVisibility(View.GONE);
//        }
//
//        // Set click listener for the comment button
//        holder.commentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION && onIssueClickListener != null) {
//                    onIssueClickListener.onIssueClicked(position);
//                }
//            }
//        });
//    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title, detail, author;
        ImageView image;
        VideoView videoView;
        ImageButton commentButton;
        CircleImageView profile;
        CardView idimagecard;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textCardTitle);
            detail = itemView.findViewById(R.id.textCardBody);
            author = itemView.findViewById(R.id.textcard_username);
            profile = itemView.findViewById(R.id.profile_icon_card);
            image = itemView.findViewById(R.id.imageView);
            videoView = itemView.findViewById(R.id.videoView);
            commentButton = itemView.findViewById(R.id.comm);
            idimagecard = itemView.findViewById(R.id.idimagecard);


        }
    }
}

//package com.example.cervical_cancer.adapters;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.MediaController;
//import android.widget.TextView;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.cervical_cancer.R;
//import com.example.cervical_cancer.modals.IssueModal;
//
//import java.util.ArrayList;
//
//import de.hdodenhof.circleimageview.CircleImageView;
//
//public class IssueAdaptor extends RecyclerView.Adapter<IssueAdaptor.MyViewHolder> {
//    private Context context;
//    private ArrayList<IssueModal> list;
//    private OnIssueClickListener onIssueClickListener;
//
//    public interface OnIssueClickListener {
//        void onIssueClicked(int position);
//    }
//
//    public IssueAdaptor(Context context, ArrayList<IssueModal> list, OnIssueClickListener onIssueClickListener) {
//        this.context = context;
//        this.list = list;
//        this.onIssueClickListener = onIssueClickListener;
//    }
//
//    public void setList(ArrayList<IssueModal> newList) {
//        list = newList;
//        notifyDataSetChanged();
//    }
//
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.text_card, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        IssueModal issueModal = list.get(position);
//        holder.title.setText(issueModal.getTitle());
//        holder.detail.setText(issueModal.getDetail());
//        holder.author.setText(issueModal.getAuthor());
//
//        // Load profile image using Glide
//        Glide.with(context)
//                .load(issueModal.getProfile())
//                .error(R.drawable.profile)
//                .into(holder.profile);
//
//        String imageUri = issueModal.getImageUri();
//        String videoUri = issueModal.getVideoUri();
//
//        // Hide both image and video views at the beginning
//        holder.image.setVisibility(View.GONE);
//        holder.videoView.setVisibility(View.GONE);
//
//        if (imageUri != null) {
//            // Show image using Glide
//            Glide.with(context)
//                    .load(imageUri)
//                    .error(R.drawable.cer)
//                    .into(holder.image);
//            holder.image.setVisibility(View.VISIBLE);
//        }
//
//        if (videoUri != null) {
//            // Show video using VideoView
//            holder.videoView.setVisibility(View.VISIBLE);
//
//            // Set video URI and start the video
//            holder.videoView.setVideoURI(Uri.parse(videoUri));
//
//            // Add media controller to the VideoView
//            MediaController mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.videoView);
//            holder.videoView.setMediaController(mediaController);
//
//            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    // Pause the video initially
//                    holder.videoView.pause();
//                }
//            });
//
//            // Set click listener for the video placeholder image
//            holder.videoView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Start playing the video when the image is clicked
//                    holder.videoView.start();
//                }
//            });
//
//            // Set an OnPreparedListener to start the video when it is ready
////            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
////                @Override
////                public void onPrepared(MediaPlayer mp) {
////                    mp.start();
////                }
////
////            });
//        }
//
//        // Set click listener for the comment button
//        holder.commentButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION && onIssueClickListener != null) {
//                    onIssueClickListener.onIssueClicked(position);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView title, detail, author;
//        ImageView image;
//        VideoView videoView;
//        ImageButton commentButton;
//        CircleImageView profile;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            title = itemView.findViewById(R.id.textCardTitle);
//            detail = itemView.findViewById(R.id.textCardBody);
//            author = itemView.findViewById(R.id.textcard_username);
//            profile = itemView.findViewById(R.id.profile_icon_card);
//            image = itemView.findViewById(R.id.imageView);
//            videoView = itemView.findViewById(R.id.videoView);
//            commentButton = itemView.findViewById(R.id.comm);
//        }
//    }
//}

//package com.example.cervical_cancer.adapters;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.MediaController;
//import android.widget.TextView;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.cervical_cancer.R;
//import com.example.cervical_cancer.modals.IssueModal;
//
//import java.util.ArrayList;
//
//public class IssueAdaptor extends RecyclerView.Adapter<IssueAdaptor.MyViewHolder> {
//    private Context context;
//    private ArrayList<IssueModal> list;
//    private OnIssueClickListener onIssueClickListener;
//
//    public interface OnIssueClickListener {
//        void onIssueClicked(int position);
//    }
//
//    public IssueAdaptor(Context context, ArrayList<IssueModal> list, OnIssueClickListener onIssueClickListener) {
//        this.context = context;
//        this.list = list;
//        this.onIssueClickListener = onIssueClickListener;
//    }
//
//    public void setList(ArrayList<IssueModal> newList) {
//        list = newList;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.text_card, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        IssueModal issueModal = list.get(position);
//        holder.title.setText(issueModal.getTitle());
//        holder.detail.setText(issueModal.getDetail());
//        holder.author.setText(issueModal.getAuthor());
//
//        // Load profile image using Glide
//        Glide.with(context)
//                .load(issueModal.getProfile())
//                .error(R.drawable.profile)
//                .into(holder.profile);
//        String imageUri = issueModal.getImageUri();
//       String videoUri = issueModal.getVideoUri();
////
////        Uri imageUri = issueModal.getImageUri();
////        Uri videoUri = issueModal.getVideoUri();
//
//        // Hide both image and video views at the beginning
//        holder.image.setVisibility(View.GONE);
//        holder.videoView.setVisibility(View.GONE);
//
//        if (imageUri != null) {
//            // Show image using Glide
//            Glide.with(context)
//                    .load(imageUri)
//                    .error(R.drawable.cer)
//                    .into(holder.image);
//            holder.image.setVisibility(View.VISIBLE);
//        }
//
//        if (videoUri != null) {
//            // Show video using VideoView
//            holder.videoView.setVisibility(View.VISIBLE);
//
//            // Set video URI and start the video
//            holder.videoView.setVideoURI(Uri.parse(videoUri));
////            holder.videoView.setVideoURI(videoUri);
//
//            // Add media controller to the VideoView
//            MediaController mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.videoView);
//            holder.videoView.setMediaController(mediaController);
//
//            // Set an OnPreparedListener to start the video when it is ready
//            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
//        }
//
//        // Set click listener for the comment button
//        holder.comm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION && onIssueClickListener != null) {
//                    onIssueClickListener.onIssueClicked(position);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView title, detail, author;
//        ImageView image, profile;
//        VideoView videoView;
//        ImageButton comm;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            title = itemView.findViewById(R.id.textCardTitle);
//            detail = itemView.findViewById(R.id.textCardBody);
//            author = itemView.findViewById(R.id.textcard_username);
//            profile = itemView.findViewById(R.id.profile_icon_card);
//            image = itemView.findViewById(R.id.imageView);
//            videoView = itemView.findViewById(R.id.videoView);
//            comm = itemView.findViewById(R.id.comm);
//        }
//    }
//}

//package com.example.cervical_cancer.adapters;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.MediaController;
//import android.widget.TextView;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.cervical_cancer.R;
//import com.example.cervical_cancer.modals.IssueModal;
//
//import java.util.ArrayList;
//
//public class IssueAdaptor extends RecyclerView.Adapter<IssueAdaptor.MyViewHolder> {
//    private Context context;
//    private ArrayList<IssueModal> list;
//    private OnIssueClickListener onIssueClickListener;
//
//    public interface OnIssueClickListener {
//        void onIssueClicked(int position);
//    }
//
//    public IssueAdaptor(Context context, ArrayList<IssueModal> list, OnIssueClickListener onIssueClickListener) {
//        this.context = context;
//        this.list = list;
//        this.onIssueClickListener = onIssueClickListener;
//    }
//
//    public void setList(ArrayList<IssueModal> newList) {
//        list = newList;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.text_card, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        IssueModal issueModal = list.get(position);
//        holder.title.setText(issueModal.getTitle());
//        holder.detail.setText(issueModal.getDetail());
//        holder.author.setText(issueModal.getAuthor());
//
//        // Load profile image using Glide
//        Glide.with(context)
//                .load(issueModal.getProfile())
//                .error(R.drawable.profile)
//                .into(holder.profile);
//
//        String imageUri = issueModal.getImageUri();
//        String videoUri = issueModal.getVideoUri();
//
//        // Hide both image and video views at the beginning
//        holder.image.setVisibility(View.GONE);
//        holder.videoView.setVisibility(View.GONE);
//
//        if (imageUri != null && !imageUri.isEmpty()) {
//            // Show image using Glide
//            Glide.with(context)
//                    .load(imageUri)
//                    .error(R.drawable.cer)
//                    .into(holder.image);
//            holder.image.setVisibility(View.VISIBLE);
//        }
//
//        if (videoUri != null && !videoUri.isEmpty()) {
//            // Show video using VideoView
//            holder.videoView.setVisibility(View.VISIBLE);
//
//            // Set video URI and start the video
//            holder.videoView.setVideoURI(Uri.parse(videoUri));
//
//            // Add media controller to the VideoView
//            MediaController mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.videoView);
//            holder.videoView.setMediaController(mediaController);
//
//            // Set an OnPreparedListener to start the video when it is ready
//            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
//        }
//
//        // Set click listener for the comment button
//        holder.comm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION && onIssueClickListener != null) {
//                    onIssueClickListener.onIssueClicked(position);
//                }
//            }
//        });
//    }
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView title, detail, author;
//        ImageView image, profile;
//        VideoView videoView;
//        ImageButton comm;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            title = itemView.findViewById(R.id.textCardTitle);
//            detail = itemView.findViewById(R.id.textCardBody);
//            author = itemView.findViewById(R.id.textcard_username);
//            profile = itemView.findViewById(R.id.profile_icon_card);
//            image = itemView.findViewById(R.id.imageView);
//            videoView = itemView.findViewById(R.id.videoView);
//            comm = itemView.findViewById(R.id.comm);
//        }
//    }
//}


//package com.example.cervical_cancer.adapters;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.MediaController;
//import android.widget.TextView;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.cervical_cancer.R;
//import com.example.cervical_cancer.modals.IssueModal;
//
//import java.util.ArrayList;
//
//public class IssueAdaptor extends RecyclerView.Adapter<IssueAdaptor.MyViewHolder> {
//    private Context context;
//    private ArrayList<IssueModal> list;
//    private OnIssueClickListener onIssueClickListener;
//
//    public interface OnIssueClickListener {
//        void onIssueClicked(int position);
//    }
//
//    public IssueAdaptor(Context context, ArrayList<IssueModal> list, OnIssueClickListener onIssueClickListener) {
//        this.context = context;
//        this.list = list;
//        this.onIssueClickListener = onIssueClickListener;
//    }
//
//    public void setList(ArrayList<IssueModal> newList) {
//        list = newList;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.text_card, parent, false);
//        return new MyViewHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        IssueModal issueModal = list.get(position);
//        holder.title.setText(issueModal.getTitle());
//        holder.detail.setText(issueModal.getDetail());
//        holder.author.setText(issueModal.getAuthor());
//        holder.profile.setImageURI(Uri.parse(issueModal.getProfile()));
//
////        holder.profile.setImageURI(issueModal.getProfile());
//
//
//        String imageUri = issueModal.getImageUri();
//        String videoUri = issueModal.getVideoUri();
//
//        // Hide both image and video views at the beginning
//        holder.image.setVisibility(View.GONE);
//        holder.videoView.setVisibility(View.GONE);
//
//        if (imageUri != null && !imageUri.isEmpty()) {
//            // Show image using Glide
//            Glide.with(context)
//                    .load(imageUri)
//                    .error(R.drawable.cer)
//                    .into(holder.image);
//            holder.image.setVisibility(View.VISIBLE);
//        }
//
//        if (videoUri != null && !videoUri.isEmpty()) {
//            // Show video using VideoView
//            holder.videoView.setVisibility(View.VISIBLE);
//
//            // Set video URI and start the video
//            holder.videoView.setVideoURI(Uri.parse(videoUri));
//
//            // Add media controller to the VideoView
//            MediaController mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.videoView);
//            holder.videoView.setMediaController(mediaController);
//
//            // Set an OnPreparedListener to start the video when it is ready
//            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
//        }
//
//        // Set click listener for the comment button
//        holder.comm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION && onIssueClickListener != null) {
//                    onIssueClickListener.onIssueClicked(position);
//                }
//            }
//        });
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView title, detail, author;
//        ImageView image , profile;
//        VideoView videoView;
//        ImageButton comm;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            title = itemView.findViewById(R.id.textCardTitle);
//            detail = itemView.findViewById(R.id.textCardBody);
//            author = itemView.findViewById(R.id.textcard_username);
//            profile = itemView.findViewById(R.id.profile_icon_card);
//            image = itemView.findViewById(R.id.imageView);
//            videoView = itemView.findViewById(R.id.videoView);
//            comm = itemView.findViewById(R.id.comm);
//        }
//    }
//}
//
//


//package com.example.cervical_cancer.adapters;
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageButton;
//import android.widget.ImageView;
//import android.widget.MediaController;
//import android.widget.TextView;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.cervical_cancer.R;
//import com.example.cervical_cancer.modals.IssueModal;
//
//import java.util.ArrayList;
//
//public class IssueAdaptor extends RecyclerView.Adapter<IssueAdaptor.MyViewHolder> {
//    private Context context;
//    private ArrayList<IssueModal> list;
//    private OnIssueClickListener onIssueClickListener;
//
//    public interface OnIssueClickListener {
//        void onIssueClicked(int position);
//    }
//
//    public IssueAdaptor(Context context, ArrayList<IssueModal> list, OnIssueClickListener onIssueClickListener) {
//        this.context = context;
//        this.list = list;
//        this.onIssueClickListener = onIssueClickListener;
//    }
//
//    public void setList(ArrayList<IssueModal> newList) {
//        list = newList;
//        notifyDataSetChanged();
//    }
//
//    @NonNull
//    @Override
//    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(context).inflate(R.layout.text_card, parent, false);
//        return new MyViewHolder(v);
//    }
//
//
//
//    @Override
//    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//        IssueModal issueModal = list.get(position);
//        holder.title.setText(issueModal.getTitle());
//        holder.detail.setText(issueModal.getDetail());
//        holder.author.setText(issueModal.getAuthor());
//
//        String imageUri = issueModal.getImageUri();
//        String videoUri = issueModal.getVideoUri();
//
//        // Hide both image and video views at the beginning
//        holder.image.setVisibility(View.GONE);
//        holder.videoView.setVisibility(View.GONE);
//
//        if (imageUri != null && !imageUri.isEmpty()) {
//            // Show image using Glide
//            Glide.with(context)
//                    .load(imageUri)
//                    .error(R.drawable.cer)
//                    .into(holder.image);
//            holder.image.setVisibility(View.VISIBLE);
//        }
//
//        if (videoUri != null && !videoUri.isEmpty()) {
//            // Show video using VideoView
//            holder.videoView.setVisibility(View.VISIBLE);
//
//            // Set video URI and start the video
//            holder.videoView.setVideoURI(Uri.parse(videoUri));
//
//            // Add media controller to the VideoView
//            MediaController mediaController = new MediaController(context);
//            mediaController.setAnchorView(holder.videoView);
//            holder.videoView.setMediaController(mediaController);
//
//            // Set an OnPreparedListener to start the video when it is ready
//            holder.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                @Override
//                public void onPrepared(MediaPlayer mp) {
//                    mp.start();
//                }
//            });
//        }
//
//        // Set click listener for the comment button
//        holder.comm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                if (position != RecyclerView.NO_POSITION && onIssueClickListener != null) {
//                    onIssueClickListener.onIssueClicked(position);
//                }
//            }
//        });
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//
//    public class MyViewHolder extends RecyclerView.ViewHolder {
//        TextView title, detail, author;
//        ImageView image;
//        VideoView videoView;
//        ImageButton comm;
//
//        public MyViewHolder(@NonNull View itemView) {
//            super(itemView);
//
//            title = itemView.findViewById(R.id.textCardTitle);
//            detail = itemView.findViewById(R.id.textCardBody);
//            author = itemView.findViewById(R.id.textcard_username);
//            image = itemView.findViewById(R.id.imageView);
//            videoView = itemView.findViewById(R.id.videoView);
//            comm = itemView.findViewById(R.id.comm);
//        }
//    }
//}
//package com.example.cervical_cancer.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.cervical_cancer.R;
//import com.example.cervical_cancer.modals.CommentModal;
//
//import java.util.ArrayList;
//
//public class CommentAdaptor extends RecyclerView.Adapter<CommentAdaptor.CommentHolder> {
//    private Context context;
//    private ArrayList<CommentModal> comments;
//
//    public CommentAdaptor(Context context, ArrayList<CommentModal> comment) {
//        this.context = context;
//        this.comments = comment;
//    }
//
//    @NonNull
//    @Override
//    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(context).inflate(R.layout.commentholder, parent, false);
//        return new CommentHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
//        holder.commentView.setText(comments.get(position).getComment());
//        holder.authorView.setText(comments.get(position).getAuthor());
//    }
//
//    @Override
//    public int getItemCount() {
//        return comments.size();
//    }
//
//    class CommentHolder extends RecyclerView.ViewHolder{
//        TextView commentView;
//        TextView authorView;
//        public CommentHolder(@NonNull View itemView) {
//            super(itemView);
//            commentView = itemView.findViewById(R.id.comment_txt);
//            authorView = itemView.findViewById(R.id.username_cmt);
//        }
//    }
//}
//
//