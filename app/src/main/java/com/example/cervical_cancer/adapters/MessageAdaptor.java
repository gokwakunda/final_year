package com.example.cervical_cancer.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cervical_cancer.R;
import com.example.cervical_cancer.modals.Message;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdaptor extends RecyclerView.Adapter<MessageAdaptor.MessageHolder> {
    private ArrayList<Message> messages;
    private String senderImg, receiverImg;
    private Context context;

    public MessageAdaptor(ArrayList<Message> messages, String senderImg, String receiverImg, Context context) {
        this.messages = messages;
        this.senderImg = senderImg;
        this.receiverImg = receiverImg;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.messageholder, parent, false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        Message message = messages.get(position);
        holder.txtMessage.setText(message.getContext());
        ConstraintLayout constraintLayout = holder.constraintLayout;

        if (message.getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())) {
            Glide.with(context).load(senderImg)
                    .error(R.drawable.account_img)
                    .placeholder(R.drawable.account_img)
                    .into(holder.profileImage);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.profile_cardView, ConstraintSet.START);
            constraintSet.clear(R.id.txtMessageContent, ConstraintSet.START);
            constraintSet.connect(R.id.profile_cardView, ConstraintSet.END, R.id.cclayout, ConstraintSet.END, 0);
            constraintSet.connect(R.id.txtMessageContent, ConstraintSet.END, R.id.profile_cardView, ConstraintSet.START, 0);
            constraintSet.applyTo(constraintLayout);
        } else {
            Glide.with(context).load(receiverImg)
                    .error(R.drawable.account_img)
                    .placeholder(R.drawable.account_img)
                    .into(holder.profileImage);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.profile_cardView, ConstraintSet.END);
            constraintSet.clear(R.id.txtMessageContent, ConstraintSet.END);
            constraintSet.connect(R.id.profile_cardView, ConstraintSet.START, R.id.cclayout, ConstraintSet.START, 0);
            constraintSet.connect(R.id.txtMessageContent, ConstraintSet.START, R.id.profile_cardView, ConstraintSet.END, 0);
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class MessageHolder extends RecyclerView.ViewHolder {
        ConstraintLayout constraintLayout;
        TextView txtMessage;
        ImageView profileImage;

        MessageHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.cclayout);
            txtMessage = itemView.findViewById(R.id.txtMessageContent);
            profileImage = itemView.findViewById(R.id.small_profile_img);
        }
    }
}
