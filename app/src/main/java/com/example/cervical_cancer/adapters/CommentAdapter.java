package com.example.cervical_cancer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.cervical_cancer.R;
import com.example.cervical_cancer.modals.CommentModal;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
    private Context context;
    private ArrayList<CommentModal> comments;

    public CommentAdapter(Context context, ArrayList<CommentModal> comments) {
        this.context = context;
        this.comments = comments;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.commentholder, parent, false);
        return new CommentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {
        CommentModal comment = comments.get(position);
        holder.commentView.setText(comment.getComment());
        holder.usernameView.setText(comment.getAuthor());

        String profilePicture = comment.getProfile();
        if (profilePicture != null && !profilePicture.isEmpty()) {
            RequestOptions options = new RequestOptions()
                    .circleCrop()
                    .placeholder(R.drawable.account_img) // Placeholder image while loading or if profilePicture is null
                    .error(R.drawable.account_img); // Error image if the load fails

            Glide.with(context)
                    .load(profilePicture)
                    .apply(options)
                    .diskCacheStrategy(DiskCacheStrategy.DATA) // Cache the profile picture
                    .into(holder.profileImage);
        } else {
            // Load a default profile picture if profilePicture is null or empty
            holder.profileImage.setImageResource(R.drawable.account_img);
        }
    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    static class CommentHolder extends RecyclerView.ViewHolder {
        TextView commentView;
        TextView usernameView;
        ImageView profileImage;

        CommentHolder(@NonNull View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_img);
            commentView = itemView.findViewById(R.id.comment_txt);
            usernameView = itemView.findViewById(R.id.username_cmt);
        }
    }
}

//package com.example.cervical_cancer.adapters;
//
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
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
//public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentHolder> {
//    private Context context;
//    private ArrayList<CommentModal> comments;
//
//    public CommentAdapter(Context context, ArrayList<CommentModal> comments) {
//        this.context = context;
//        this.comments = comments;
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
//        CommentModal comment = comments.get(position);
//        holder.commentView.setText(comment.getComment());
//        holder.usernameView.setText(comment.getAuthor());
//    }
//
//    @Override
//    public int getItemCount() {
//        return comments.size();
//    }
//
//    class CommentHolder extends RecyclerView.ViewHolder {
//        TextView commentView;
//        TextView usernameView;
//        ImageView profileImage; // Add ImageView for profile picture
//        public CommentHolder(@NonNull View itemView) {
//            super(itemView);
//            profileImage = itemView.findViewById(R.id.profile_img); // Find profile image view by ID
//            commentView = itemView.findViewById(R.id.comment_txt);
//            usernameView = itemView.findViewById(R.id.username_cmt);
//        }
//    }
//}

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
//import java.util.Map;
//
//public class CommentAdaptor extends RecyclerView.Adapter<CommentAdaptor.CommentHolder> {
//    private Context context;
//    private String postID;
//    private ArrayList<CommentModal> comments;
//    private Map<String, ArrayList<CommentModal>> commentsMap;
//
//    public CommentAdaptor(Context context, Map<String, ArrayList<CommentModal>> commentsMap) {
//        this.context = context;
//        this.comments = comments;
//        this.commentsMap = commentsMap;
//
//
//       }
//
//    public void setPostID(String postID) {
//        this.postID = postID;
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
//        CommentModal comment = comments.get(position);
//        holder.commentView.setText(comment.getComment());
//        holder.authorView.setText(comment.getAuthor());
//    }
//
//    @Override
//    public int getItemCount() {
//        return comments.size();
//    }
//
//    static class CommentHolder extends RecyclerView.ViewHolder {
//        TextView commentView;
//        TextView authorView;
//
//        CommentHolder(@NonNull View itemView) {
//            super(itemView);
//            commentView = itemView.findViewById(R.id.comment_txt);
//            authorView = itemView.findViewById(R.id.username_cmt);
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
//import com.example.cervical_cancer.modals.CommentModal;
//import com.example.cervical_cancer.R;
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
