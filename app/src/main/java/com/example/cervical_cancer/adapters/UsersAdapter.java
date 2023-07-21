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
import com.example.cervical_cancer.R;
import com.example.cervical_cancer.modals.User;

import java.util.ArrayList;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UserHolder> {
    private ArrayList<User> users;
    private Context context;
    private OnUserClickListener onUserClickListener;

    public interface OnUserClickListener {
        void onUserClicked(int position);
    }

    public UsersAdapter(ArrayList<User> users, Context context, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.context = context;
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    @Override
    public UserHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.userholder, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserHolder holder, int position) {
        User user = users.get(position);
        holder.txtUsername.setText(user.getUsername());

        if (user.hasUnreadMessages()) {
            holder.redDot.setVisibility(View.VISIBLE);
        } else {
            holder.redDot.setVisibility(View.GONE);
        }

        Glide.with(context)
                .load(user.getProfilePicture())
                .error(R.drawable.account_img)
                .placeholder(R.drawable.account_img)
                .into(holder.profileImg);
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
        notifyDataSetChanged();
    }

    public void markUserAsUnread(int position) {
        if (position >= 0 && position < users.size()) {
            User user = users.get(position);
            user.setHasUnreadMessages(true);
            notifyItemChanged(position);
        }
    }

    class UserHolder extends RecyclerView.ViewHolder {
        TextView txtUsername;
        ImageView profileImg;
        ImageView redDot;

        UserHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onUserClickListener.onUserClicked(position);
                    }
                }
            });
            txtUsername = itemView.findViewById(R.id.txtUsername);
            profileImg = itemView.findViewById(R.id.img_profile);
            redDot = itemView.findViewById(R.id.unreadIndicator);
        }
    }
}
