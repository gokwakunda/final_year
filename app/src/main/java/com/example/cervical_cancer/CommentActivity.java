package com.example.cervical_cancer;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.request.RequestOptions;
import com.example.cervical_cancer.adapters.CommentAdapter;
import com.example.cervical_cancer.modals.CommentModal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CommentActivity extends AppCompatActivity {
    private EditText commentField;
    private CommentAdapter commentAdapter;
    private String postId;
    private ImageView sendImgBtn, profile;
    private TextView messageTxt ,username;
    private RecyclerView commentRv;
    private DatabaseReference commentsRef;
    private ArrayList<CommentModal> comments;
    private DatabaseReference currentUserRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        username = findViewById(R.id.username_cmt);
        comments = new ArrayList<>();
        commentAdapter = new CommentAdapter(this, comments);
        commentField = findViewById(R.id.editCmtInput);
        sendImgBtn = findViewById(R.id.sendCmtBtn);
        profile = findViewById(R.id.profile_img);

        postId = getIntent().getStringExtra("id");
        if (postId == null) {
            // Handle the case where postId is null
            finish();
            return;
        }
        commentsRef = FirebaseDatabase.getInstance().getReference("issue_comments").child(postId);
        commentRv = findViewById(R.id.comment_rv);
        commentRv.setHasFixedSize(true);
        commentRv.setLayoutManager(new LinearLayoutManager(CommentActivity.this));
        messageTxt = findViewById(R.id.idTVMsg);
        messageTxt.setText(postId);

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        if (currentUser != null) {
            String userId = currentUser.getUid();
            currentUserRef = FirebaseDatabase.getInstance().getReference("user").child(userId);
            fetchUserData();
        }

        getComments();


        sendImgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String commentText = commentField.getText().toString();
                if (!commentText.isEmpty() && commentsRef != null) {
                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser != null) {
                        String userId = currentUser.getUid();
                        currentUserRef = FirebaseDatabase.getInstance().getReference("user").child(userId);
                        currentUserRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if (snapshot.exists()) {
                                    String username = snapshot.child("username").getValue(String.class);
                                    String profilePicture = snapshot.child("profilePicture").getValue(String.class);

                                    // Check if profilePicture and username are not null
                                    if (profilePicture != null && username != null) {
                                        CommentModal comment = new CommentModal(profilePicture, postId, commentText, username);
                                        // Change "push()" to "setValue()" to save the comment under the "issue_comments" node
                                        commentsRef.push().setValue(comment)
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            commentField.setText("");
                                                        } else {
                                                            // Handle the error
                                                        }
                                                    }
                                                });
                                    } else {
                                        // Handle the case where profilePicture or username is null
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                // Handle the error
                            }
                        });
                    }
                }
            }
        });


    }
    private void fetchUserData() {
        currentUserRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String profilePicture = snapshot.child("profilePicture").getValue(String.class);
                    String username = snapshot.child("username").getValue(String.class);
                    Log.d("UserData", "Profile Picture: " + profilePicture);
                    Log.d("UserData", "Username: " + username);

                    RequestOptions options = new RequestOptions()
                            .circleCrop()
                            .placeholder(R.drawable.account_img)
                            .error(R.drawable.account_img);

                    // ... (rest of the code)
                } else {
                    Log.e("UserData", "Snapshot does not exist!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("UserData", "Error fetching user data: " + error.getMessage());
            }
        });
    }


    private void getComments() {
        commentsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                comments.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    CommentModal comment = dataSnapshot.getValue(CommentModal.class);
                    comments.add(comment);
                }
                commentAdapter.notifyDataSetChanged();
                commentRv.setAdapter(commentAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle the error
            }
        });
    }
}
