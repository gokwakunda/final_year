package com.example.cervical_cancer;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.cervical_cancer.adapters.MessageAdaptor;
import com.example.cervical_cancer.adapters.UsersAdapter;
import com.example.cervical_cancer.modals.Message;
import com.example.cervical_cancer.modals.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity implements UsersAdapter.OnUserClickListener {
    private RecyclerView recyclerView;
    private EditText editTextInput;
    private TextView txtChattingWith;
    private ImageView toolImgView, imgSend;
    private ProgressBar progressBar;

    private UsersAdapter usersAdapter;
    private String chatRoomId;
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();
    private MessageAdaptor messageAdapter;

    private String receiverEmail;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        recyclerView = findViewById(R.id.recyclerMsg);
        editTextInput = findViewById(R.id.editMsgInput);
        txtChattingWith = findViewById(R.id.txtChattingWith);
        progressBar = findViewById(R.id.msgProgressBar);
        toolImgView = findViewById(R.id.img_toolbar);
        imgSend = findViewById(R.id.sendMsgBtn);

        String username_of_roommate = getIntent().getStringExtra("username_of_roommate");
        receiverEmail = getIntent().getStringExtra("email_of_roommate");
        String my_img = getIntent().getStringExtra("my_img");
        String image_of_roommate = getIntent().getStringExtra("image_of_roommate");

        txtChattingWith.setText(username_of_roommate);

        usersAdapter = new UsersAdapter(users, this, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(usersAdapter);

        messageAdapter = new MessageAdaptor(messages, my_img, image_of_roommate, this);
        recyclerView.setAdapter(messageAdapter);

        imgSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = editTextInput.getText().toString();
                if (!messageText.isEmpty()) {
                    sendMessage(receiverEmail, messageText);
                    editTextInput.setText("");
                }
            }
        });

        Glide.with(this).load(image_of_roommate)
                .placeholder(R.drawable.account_img)
                .error(R.drawable.account_img)
                .into(toolImgView);

        setUpChatRoom(username_of_roommate);

        attachMessageListener();
    }

    private void setUpChatRoom(String username_of_roommate) {
        String myUsername = FirebaseAuth.getInstance().getCurrentUser().getDisplayName();
        if (username_of_roommate != null && myUsername != null) {
            if (username_of_roommate.compareTo(myUsername) > 0) {
                chatRoomId = myUsername + "-" + username_of_roommate;
            } else if (username_of_roommate.compareTo(myUsername) < 0) {
                chatRoomId = username_of_roommate + "-" + myUsername;
            } else {
                // The usernames are the same, handle the condition accordingly
                chatRoomId = username_of_roommate; // Assign a unique chatRoomId in case of same usernames
            }
        } else {
            // Handle the case when either username_of_roommate or myUsername is null
            // Assign a unique chatRoomId based on available information
        }
    }

    private void attachMessageListener() {
        FirebaseDatabase.getInstance().getReference("chats/" + chatRoomId + "/messages")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                        Message message = snapshot.getValue(Message.class);
                        if (message != null) {
                            messages.add(message);
                            messageAdapter.notifyDataSetChanged();
                            if (message.getSender().equals(receiverEmail)) {
                                markUserAsUnread(receiverEmail);
                            }
                        }
                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot snapshot) {
                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e(TAG, "Failed to listen for messages: " + error.getMessage());
                    }
                });
    }

    private void sendMessage(String receiver, String messageText) {
        FirebaseDatabase.getInstance().getReference("chats/" + chatRoomId + "/messages")
                .push()
                .setValue(new Message(FirebaseAuth.getInstance().getCurrentUser().getEmail(), receiver, messageText));
    }

    private void markUserAsUnread(String userEmail) {
        for (User user : users) {
            if (user.getEmail().equals(userEmail)) {
                user.setHasUnreadMessages(true);
                usersAdapter.notifyDataSetChanged();
                break;
            }
        }
    }

    @Override
    public void onUserClicked(int position) {
        User selectedUser = users.get(position);
        selectedUser.setHasUnreadMessages(false);
        usersAdapter.notifyItemChanged(position);
        // Implement the logic to open the chat activity with the selected user
    }
}
