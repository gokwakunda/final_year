
        package com.example.cervical_cancer;

import static com.google.common.io.Files.getFileExtension;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.cervical_cancer.modals.IssueModal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class CreatePostFragment extends Fragment {
    private Button sendIssueBtn;
    private EditText titleField, detailField;
    private VideoView videoView;
    private ImageView imageField, userProfileImage;
    private static final int IMAGE_CODE = 1000;
    private static final int VIDEO_CODE = 2000;
    private Uri imageUri, videoUri;
    private DatabaseReference usersRef;

    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);

        titleField = view.findViewById(R.id.titleField);
        detailField = view.findViewById(R.id.detailField);
        imageField = view.findViewById(R.id.imageField);
        videoView = view.findViewById(R.id.videoField);
        sendIssueBtn = view.findViewById(R.id.sendIssueBtn);
        userProfileImage = view.findViewById(R.id.profile_icon_card);

        Button selectImageButton = view.findViewById(R.id.uploadImageButton);
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                imageIntent.setType("image/*");
                startActivityForResult(Intent.createChooser(imageIntent, "Select Image"), IMAGE_CODE);
            }
        });

        Button selectVideoButton = view.findViewById(R.id.uploadVideoButton);
        selectVideoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videoIntent = new Intent(Intent.ACTION_GET_CONTENT);
                videoIntent.setType("video/*");
                startActivityForResult(Intent.createChooser(videoIntent, "Select Video"), VIDEO_CODE);
            }
        });

        sendIssueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleField.getText().toString().trim();
                String detail = detailField.getText().toString().trim();
                String id = ""+System.currentTimeMillis();

                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(detail)) {
                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
                } else {
                    ProgressDialog progressDialog = new ProgressDialog(getActivity());
                    progressDialog.setCancelable(false);
                    progressDialog.setMessage("Posting Issue...");
                    progressDialog.show();

                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                    if (currentUser != null) {
                        String userId = currentUser.getUid();
                        usersRef = FirebaseDatabase.getInstance().getReference().child("user");

                        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    String author = dataSnapshot.child("username").getValue(String.class);
                                    String profileUriString = dataSnapshot.child("profilePicture").getValue(String.class);

                                    IssueModal issueModal = new IssueModal(id,title, detail, author, profileUriString);
                                    postIssue(issueModal, progressDialog);
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(getActivity(), "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {
                                progressDialog.dismiss();
                                Toast.makeText(getActivity(), "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "User not authenticated", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IMAGE_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            imageUri = data.getData();
            imageField.setImageURI(imageUri);
            videoView.setVisibility(View.GONE); // Hide the video view
        }

        if (requestCode == VIDEO_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            videoUri = data.getData();
            videoView.setVisibility(View.GONE); // Hide the video view

            // Set video thumbnail as the image for the ImageView
            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
            retriever.setDataSource(getActivity(), videoUri);
            Bitmap thumbnail = retriever.getFrameAtTime();
            imageField.setImageBitmap(thumbnail);
        }
    }

    private void postIssue(IssueModal issueModal, ProgressDialog progressDialog) {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("issues");
        String issueId = databaseReference.push().getKey();
        issueModal.setId(issueId);

        if (imageUri != null) {
            FirebaseStorage storage = FirebaseStorage.getInstance();
            StorageReference storageReference = storage.getReference();
            final StorageReference imageRef = storageReference.child("images/" + System.currentTimeMillis() + "." + getFileExtension(imageUri.toString()));

            UploadTask uploadTask = imageRef.putFile(imageUri);
            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()) {
                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                issueModal.setImageUri(uri.toString()); // Convert Uri to string
                                issueModal.setId(issueId);
                                saveIssue(databaseReference, issueId, issueModal, progressDialog);
                            }
                        });
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Failed to upload image", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else if (videoUri != null) {
            processVideo(databaseReference, issueId, issueModal, progressDialog);
        } else {
            issueModal.setId(issueId);
            saveIssue(databaseReference, issueId, issueModal, progressDialog);
        }
    }

    private void processVideo(DatabaseReference databaseReference, String issueId, IssueModal issueModal, ProgressDialog progressDialog) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReference();
        final StorageReference videoRef = storageReference.child("videos/" + System.currentTimeMillis() + "." + getFileExtension(videoUri.toString()));

        UploadTask uploadTask = videoRef.putFile(videoUri);
        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()) {
                    videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            issueModal.setVideoUri(uri.toString()); // Convert Uri to string
                            issueModal.setId(issueId);
                            saveIssue(databaseReference, issueId, issueModal, progressDialog);
                        }
                    });
                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Failed to upload video", Toast.LENGTH_SHORT).show();
                }
            }

//
        });
    }

    private void saveIssue(DatabaseReference databaseReference, String issueId, IssueModal issueModal, ProgressDialog progressDialog) {
        databaseReference.child(issueId).setValue(issueModal).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Issue posted successfully", Toast.LENGTH_SHORT).show();
                    clearFields();
                } else {
                    Toast.makeText(getActivity(), "Failed to post issue", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearFields() {
        titleField.setText("");
        detailField.setText("");
        imageField.setImageURI(null);
        videoView.setVideoURI(null);
    }
}



//package com.example.cervical_cancer;
//
//import static com.google.common.io.Files.getFileExtension;
//
//import android.annotation.SuppressLint;
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.media.MediaMetadataRetriever;
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.example.cervical_cancer.modals.IssueModal;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//public class CreatePostFragment extends Fragment {
//    private Button sendIssueBtn;
//    private EditText titleField, detailField;
//    private VideoView videoView;
//    private ImageView imageField, userProfileImage;
//    private static final int IMAGE_CODE = 1000;
//    private static final int VIDEO_CODE = 2000;
//    private Uri imageUri, videoUri;
//    private DatabaseReference usersRef;
//
//    @SuppressLint("MissingInflatedId")
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
//
//        titleField = view.findViewById(R.id.titleField);
//        detailField = view.findViewById(R.id.detailField);
//        imageField = view.findViewById(R.id.imageField);
//        videoView = view.findViewById(R.id.videoField);
//        sendIssueBtn = view.findViewById(R.id.sendIssueBtn);
//        userProfileImage = view.findViewById(R.id.profile_icon_card);
//
//        Button selectImageButton = view.findViewById(R.id.uploadImageButton);
//        selectImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                imageIntent.setType("image/*");
//                startActivityForResult(Intent.createChooser(imageIntent, "Select Image"), IMAGE_CODE);
//            }
//        });
//
//        Button selectVideoButton = view.findViewById(R.id.uploadVideoButton);
//        selectVideoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent videoIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                videoIntent.setType("video/*");
//                startActivityForResult(Intent.createChooser(videoIntent, "Select Video"), VIDEO_CODE);
//            }
//        });
//
//        sendIssueBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String title = titleField.getText().toString().trim();
//                String detail = detailField.getText().toString().trim();
//
//                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(detail)) {
//                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                } else {
//                    ProgressDialog progressDialog = new ProgressDialog(getActivity());
//                    progressDialog.setCancelable(false);
//                    progressDialog.setMessage("Posting Issue...");
//                    progressDialog.show();
//
//                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//                    if (currentUser != null) {
//                        String userId = currentUser.getUid();
//                        usersRef = FirebaseDatabase.getInstance().getReference().child("user");
//
////                        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
////                            @Override
////                            public void onDataChange(DataSnapshot dataSnapshot) {
////                                if (dataSnapshot.exists()) {
////                                    String author = dataSnapshot.child("username").getValue(String.class);
////
////                                    String profileUriString = dataSnapshot.child("profilePicture").getValue(String.class);
////                                    Uri profile = Uri.parse(profileUriString);
//////
//////                                    Uri profile = Uri.parse(dataSnapshot.child("profilePicture").getValue(String.class));
////
////                                    IssueModal issueModal = new IssueModal(title, detail, author, profile);
////                                    postIssue(issueModal, progressDialog);
////                                } else {
////                                    progressDialog.dismiss();
////                                    Toast.makeText(getActivity(), "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
////                                }
////                            }
////
////                            @Override
////                            public void onCancelled(DatabaseError databaseError) {
////                                progressDialog.dismiss();
////                                Toast.makeText(getActivity(), "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
////                            }
////                        });
//                        usersRef.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.exists()) {
//                                    String author = dataSnapshot.child("username").getValue(String.class);
//                                    String profileUriString = dataSnapshot.child("profilePicture").getValue(String.class);
//                                    Uri profile = Uri.parse(profileUriString);
//
//                                    IssueModal issueModal = new IssueModal(title, detail, author, profile);
//                                    postIssue(issueModal, progressDialog);
//                                } else {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(getActivity(), "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                progressDialog.dismiss();
//                                Toast.makeText(getActivity(), "Failed to retrieve user information", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "User not authenticated", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//
//        return view;
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == IMAGE_CODE && resultCode == getActivity().RESULT_OK && data != null) {
//            imageUri = data.getData();
//            imageField.setImageURI(imageUri);
//            videoView.setVisibility(View.GONE); // Hide the video view
//        }
//
//        if (requestCode == VIDEO_CODE && resultCode == getActivity().RESULT_OK && data != null) {
//            videoUri = data.getData();
//            videoView.setVisibility(View.GONE); // Hide the video view
//
//            // Set video thumbnail as the image for the ImageView
//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource(getActivity(), videoUri);
//            Bitmap thumbnail = retriever.getFrameAtTime();
//            imageField.setImageBitmap(thumbnail);
//        }
//    }
//
//    private void postIssue(IssueModal issueModal, ProgressDialog progressDialog) {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("issues");
//        String issueId = databaseReference.push().getKey();
//
//        if (imageUri != null) {
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageReference = storage.getReference();
//            final StorageReference imageRef = storageReference.child("images/" + System.currentTimeMillis() + "." + getFileExtension(imageUri.toString()));
//
//            UploadTask uploadTask = imageRef.putFile(imageUri);
//            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                issueModal.setImageUri(uri.toString()); // Convert Uri to string
//                                issueModal.setId(issueId);
//                                saveIssue(databaseReference, issueId, issueModal, progressDialog);
//                            }
//                        });
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "Failed to upload image", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        } else if (videoUri != null) {
//            processVideo(databaseReference, issueId, issueModal, progressDialog);
//        } else {
//            issueModal.setId(issueId);
//            saveIssue(databaseReference, issueId, issueModal, progressDialog);
//        }
//    }
//
////
////    private void postIssue(IssueModal issueModal, ProgressDialog progressDialog) {
////        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("issues");
////        String issueId = databaseReference.push().getKey();
////
////        if(imageUri != null) {
////            FirebaseStorage storage = FirebaseStorage.getInstance();
////            StorageReference storageReference = storage.getReference();
////            final StorageReference imageRef = storageReference.child("images/" + System.currentTimeMillis() + "." + getFileExtension(imageUri));
////
////            UploadTask uploadTask = imageRef.putFile(imageUri);
////            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
////                @Override
////                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
////                    if (task.isSuccessful()) {
////                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
////                            @Override
////                            public void onSuccess(Uri uri) {
//////                                issueModal.setImageUri();
////                                issueModal.setImageUri(uri.toString());
////
////                                issueModal.setId(issueId);
////                                saveIssue(databaseReference, issueId, issueModal, progressDialog);
////                            }
////                        });
////                    } else {
////                        progressDialog.dismiss();
////                        Toast.makeText(getActivity(), "Failed to upload image", Toast.LENGTH_SHORT).show();
////                    }
////                }
////            });
////        } else if (videoUri != null) {
////            processVideo(databaseReference, issueId, issueModal, progressDialog);
////        } else {
////            issueModal.setId(issueId);
////            saveIssue(databaseReference, issueId, issueModal, progressDialog);
////        }
////    }
//
//    private void processVideo(DatabaseReference databaseReference, String issueId, IssueModal issueModal, ProgressDialog progressDialog) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageReference = storage.getReference();
//        final StorageReference videoRef = storageReference.child("videos/" + System.currentTimeMillis() + "." + getFileExtension(videoUri.toString()));
//
//        UploadTask uploadTask = videoRef.putFile(videoUri);
//        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                if (task.isSuccessful()) {
//                    videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            issueModal.setVideoUri(uri.toString());
//                            saveIssue(databaseReference, issueId, issueModal, progressDialog);
//                        }
//                    });
//                } else {
//                    progressDialog.dismiss();
//                    Toast.makeText(getActivity(), "Failed to upload video", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void saveIssue(DatabaseReference databaseReference, String issueId, IssueModal issueModal, ProgressDialog progressDialog) {
//        DatabaseReference newIssueRef = databaseReference.child(issueId);
//
//        newIssueRef.setValue(issueModal).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                progressDialog.dismiss();
//                if (task.isSuccessful()) {
//                    Toast.makeText(getActivity(), "Issue posted successfully", Toast.LENGTH_SHORT).show();
//                    clearFields();
//                } else {
//                    Toast.makeText(getActivity(), "Failed to post issue", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void clearFields() {
//        titleField.setText("");
//        detailField.setText("");
//        imageField.setImageURI(null);
//        videoView.setVideoURI(null);
//        videoView.setVisibility(View.GONE);
//    }
//}
//


//package com.example.cervical_cancer;
//
//import android.annotation.SuppressLint;
//import android.app.ProgressDialog;
//import android.content.ContentResolver;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.media.MediaMetadataRetriever;
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.MimeTypeMap;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.example.cervical_cancer.modals.IssueModal;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.auth.FirebaseUser;
//import com.google.firebase.database.DataSnapshot;
//import com.google.firebase.database.DatabaseError;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.database.ValueEventListener;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//public class CreatePostFragment extends Fragment {
//    private Button sendIssueBtn;
//    private EditText titleField, detailField;
//    private VideoView videoView;
//    private ImageView imageField ,userProfileImage;
//    private static final int IMAGE_CODE = 1000;
//    private static final int VIDEO_CODE = 2000;
//    private Uri imageUri, videoUri;
//
//    @SuppressLint("MissingInflatedId")
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
//
//        titleField = view.findViewById(R.id.titleField);
//        detailField = view.findViewById(R.id.detailField);
//        imageField = view.findViewById(R.id.imageField);
//        videoView = view.findViewById(R.id.videoField);
//        sendIssueBtn = view.findViewById(R.id.sendIssueBtn);
//        userProfileImage =view.findViewById(R.id.profile_icon_card);
//
//        Button selectImageButton = view.findViewById(R.id.uploadImageButton);
//        selectImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                imageIntent.setType("image/*");
//                startActivityForResult(Intent.createChooser(imageIntent, "Select Image"), IMAGE_CODE);
//            }
//        });
//
//        Button selectVideoButton = view.findViewById(R.id.uploadVideoButton);
//        selectVideoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent videoIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                videoIntent.setType("video/*");
//                startActivityForResult(Intent.createChooser(videoIntent, "Select Video"), VIDEO_CODE);
//            }
//        });
//
//        sendIssueBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Add a log statement to check if the button click event is triggered
//                Log.d("CreatePostFragment", "Send Issue Button Clicked");
//
//                // Your existing code for handling the button click event
//                String title = titleField.getText().toString().trim();
//                String detail = detailField.getText().toString().trim();
//
//                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(detail) || TextUtils.isEmpty(detail)) {
//                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                } else {
//                    ProgressDialog progressDialog = new ProgressDialog(getActivity());
//                    progressDialog.setCancelable(false);
//                    progressDialog.setMessage("Posting Issue...");
//                    progressDialog.show();
//
//                    FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//                    if (currentUser != null) {
//                        String userId= currentUser.getUid();
//                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("user");
//
//                        usersRef.child(userId).child("username").addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(DataSnapshot dataSnapshot) {
//                                if (dataSnapshot.exists()) {
//                                    String author = dataSnapshot.getValue(String.class);
//
//                                    IssueModal issueModal = new IssueModal(title, detail, author);
//                                    postIssue(issueModal, progressDialog);
//                                } else {
//                                    progressDialog.dismiss();
//                                    Toast.makeText(getActivity(), "Failed to retrieve username", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(DatabaseError databaseError) {
//                                progressDialog.dismiss();
//                                Toast.make Text(getActivity(), "Failed to retrieve username", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "User not authenticated", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            }
//        });
//
//        return view;
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == IMAGE_CODE && resultCode == getActivity().RESULT_OK && data != null) {
//            imageUri = data.getData();
//            imageField.setImageURI(imageUri);
//            videoView.setVisibility(View.GONE); // Hide the video view
//        }
//
//        if (requestCode == VIDEO_CODE && resultCode == getActivity().RESULT_OK && data != null) {
//            videoUri = data.getData();
//            videoView.setVisibility(View.GONE); // Hide the video view
//
//            // Set video thumbnail as the image for the ImageView
//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource(getActivity(), videoUri);
//            Bitmap thumbnail = retriever.getFrameAtTime();
//            imageField.setImageBitmap(thumbnail);
//        }
//    }
//
//    private void postIssue(IssueModal issueModal, ProgressDialog progressDialog) {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("issues");
//        String issueId = databaseReference.push().getKey();
//
//        if (imageUri != null) {
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageReference = storage.getReference();
//            final StorageReference imageRef = storageReference.child("images/" + System.currentTimeMillis() + "." + getFileExtension(imageUri));
//
//            UploadTask uploadTask = imageRef.putFile(imageUri);
//            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                issueModal.setImageUri(uri.toString());
//                                issueModal.setId(issueId);
//                                saveIssue(databaseReference, issueId, issueModal, progressDialog);
//                            }
//                        });
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "Failed to upload image", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        } else if (videoUri != null) {
//            processVideo(databaseReference, issueId, issueModal, progressDialog);
//        } else {
//            issueModal.setId(issueId);
//            saveIssue(databaseReference, issueId, issueModal, progressDialog);
//        }
//    }
//
//    private void processVideo(DatabaseReference databaseReference, String issueId, IssueModal issueModal, ProgressDialog progressDialog) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageReference = storage.getReference();
//        final StorageReference videoRef = storageReference.child("videos/" + System.currentTimeMillis() + "." + getFileExtension(videoUri));
//
//        UploadTask uploadTask = videoRef.putFile(videoUri);
//        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                if (task.isSuccessful()) {
//                    videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            issueModal.setVideoUri(uri.toString());
//                            saveIssue(databaseReference, issueId, issueModal, progressDialog);
//                        }
//                    });
//                } else {
//                    progressDialog.dismiss();
//                    Toast.makeText(getActivity(), "Failed to upload video", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//    private void saveIssue(DatabaseReference databaseReference, String issueId, IssueModal issueModal, ProgressDialog progressDialog) {
//        DatabaseReference newIssueRef = databaseReference.child(issueId);
//
//        newIssueRef.child("title").setValue(issueModal.getTitle());
//        newIssueRef.child("detail").setValue(issueModal.getDetail());
//        newIssueRef.child("author").setValue(issueModal.getAuthor());
//
//        if (issueModal.getImageUri() != null) {
//            newIssueRef.child("imageUri").setValue(issueModal.getImageUri()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        saveVideoUri(newIssueRef, issueModal, progressDialog);
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "Failed to save image URI", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        } else {
//            saveVideoUri(newIssueRef, issueModal, progressDialog);
//        }
//    }
//
//    private void saveVideoUri(DatabaseReference newIssueRef, IssueModal issueModal, ProgressDialog progressDialog) {
//        if (issueModal.getVideoUri() != null) {
//            newIssueRef.child("videoUri").setValue(issueModal.getVideoUri()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "post posted successfully", Toast.LENGTH_SHORT).show();
//                        clearFields();
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "Failed to save video URI", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        } else {
//            progressDialog.dismiss();
//            Toast.makeText(getActivity(), "Issue posted successfully", Toast.LENGTH_SHORT).show();
//            clearFields();
//        }
//    }
//
//    private String getFileExtension(Uri uri) {
//        ContentResolver contentResolver = getActivity().getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//
//    private void clearFields() {
//        titleField.setText("");
//        detailField.setText("");
//        imageField.setImageResource(R.drawable.cer);
//        videoView.setVideoURI(null);
//        imageUri = null;
//        videoUri = null;
//    }
//}




//package com.example.cervical_cancer;
//
//import android.app.ProgressDialog;
//import android.content.ContentResolver;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.media.MediaMetadataRetriever;
//import android.net.Uri;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.webkit.MimeTypeMap;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.Toast;
//import android.widget.VideoView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//
//import com.example.cervical_cancer.modals.IssueModal;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.OnSuccessListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.FirebaseAuth;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.storage.FirebaseStorage;
//import com.google.firebase.storage.StorageReference;
//import com.google.firebase.storage.UploadTask;
//
//public class CreatePostFragment extends Fragment {
//    private Button sendIssueBtn;
//    private EditText titleField, detailField, authorField;
//    private VideoView videoView;
//    private ImageView imageField;
//    private static final int IMAGE_CODE = 1000;
//    private static final int VIDEO_CODE = 2000;
//    private Uri imageUri, videoUri;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
//
//        titleField = view.findViewById(R.id.titleField);
//        detailField = view.findViewById(R.id.detailField);
////        authorField = view.findViewById(R.id.authorField);
//        imageField = view.findViewById(R.id.imageField);
//        videoView = view.findViewById(R.id.videoField);
//        sendIssueBtn = view.findViewById(R.id.sendIssueBtn);
//
//        Button selectImageButton = view.findViewById(R.id.uploadImageButton);
//        selectImageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent imageIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                imageIntent.setType("image/*");
//                startActivityForResult(Intent.createChooser(imageIntent, "Select Image"), IMAGE_CODE);
//            }
//        });
//
//        Button selectVideoButton = view.findViewById(R.id.uploadVideoButton);
//        selectVideoButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent videoIntent = new Intent(Intent.ACTION_GET_CONTENT);
//                videoIntent.setType("video/*");
//                startActivityForResult(Intent.createChooser(videoIntent, "Select Video"), VIDEO_CODE);
//            }
//        });
//
//        sendIssueBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Add a log statement to check if the button click event is triggered
//                Log.d("CreatePostFragment", "Send Issue Button Clicked");
//
//                // Your existing code for handling the button click event
//                String title = titleField.getText().toString().trim();
//                String detail = detailField.getText().toString().trim();
////                String author = authorField.getText().toString().trim();
//                String author = FirebaseAuth.getInstance().getCurrentUser().getEmail();
////                String randId = "" +System.currentTimeMillis();
//
//                if (TextUtils.isEmpty(title) || TextUtils.isEmpty(detail) || TextUtils.isEmpty(author)) {
//                    Toast.makeText(getActivity(), "Please fill in all fields", Toast.LENGTH_SHORT).show();
//                } else {
//                    IssueModal issueModal = new IssueModal(title, detail, author);
////                    issueModal.setAuthor(author);
//                    ProgressDialog progressDialog = new ProgressDialog(getActivity());
//                    progressDialog.setCancelable(false);
//                    progressDialog.setMessage("Posting Issue...");
//                    progressDialog.show();
//                    postIssue(issueModal, progressDialog);
//                }
//            }
//        });
//
//
//        return view;
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == IMAGE_CODE && resultCode == getActivity().RESULT_OK && data != null) {
//            imageUri = data.getData();
//            imageField.setImageURI(imageUri);
//            videoView.setVisibility(View.GONE); // Hide the video view
//        }
//
//        if (requestCode == VIDEO_CODE && resultCode == getActivity().RESULT_OK && data != null) {
//            videoUri = data.getData();
//            videoView.setVisibility(View.GONE); // Hide the video view
//
//            // Set video thumbnail as the image for the ImageView
//            MediaMetadataRetriever retriever = new MediaMetadataRetriever();
//            retriever.setDataSource(getActivity(), videoUri);
//            Bitmap thumbnail = retriever.getFrameAtTime();
//            imageField.setImageBitmap(thumbnail);
//        }
//    }
//
//    private void postIssue(IssueModal issueModal, ProgressDialog progressDialog) {
//        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("issues");
//        String issueId = databaseReference.push().getKey();
//
//        if (imageUri != null) {
//            FirebaseStorage storage = FirebaseStorage.getInstance();
//            StorageReference storageReference = storage.getReference();
//            final StorageReference imageRef = storageReference.child("images/" + System.currentTimeMillis() + "." + getFileExtension(imageUri));
//
//            UploadTask uploadTask = imageRef.putFile(imageUri);
//            uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                    if (task.isSuccessful()) {
//                        imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                            @Override
//                            public void onSuccess(Uri uri) {
//                                issueModal.setImageUri(uri.toString());
//                                issueModal.setId(issueId);
//                                saveIssue(databaseReference, issueId, issueModal, progressDialog);
//                            }
//                        });
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "Failed to upload image", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        } else if (videoUri != null) {
//            processVideo(databaseReference, issueId, issueModal, progressDialog);
//        } else {
//            issueModal.setId(issueId);
//            saveIssue(databaseReference, issueId, issueModal, progressDialog);
//        }
//    }
//
//    private void processVideo(DatabaseReference databaseReference, String issueId, IssueModal issueModal, ProgressDialog progressDialog) {
//        FirebaseStorage storage = FirebaseStorage.getInstance();
//        StorageReference storageReference = storage.getReference();
//        final StorageReference videoRef = storageReference.child("videos/" + System.currentTimeMillis() + "." + getFileExtension(videoUri));
//
//        UploadTask uploadTask = videoRef.putFile(videoUri);
//        uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                if (task.isSuccessful()) {
//                    videoRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            issueModal.setVideoUri(uri.toString());
//                            saveIssue(databaseReference, issueId, issueModal, progressDialog);
//                        }
//                    });
//                } else {
//                    progressDialog.dismiss();
//                    Toast.makeText(getActivity(), "Failed to upload video", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//
//private void saveIssue(DatabaseReference databaseReference, String issueId, IssueModal issueModal, ProgressDialog progressDialog) {
//    DatabaseReference newIssueRef = databaseReference.child(issueId);
//
//    newIssueRef.child("title").setValue(issueModal.getTitle());
//    newIssueRef.child("detail").setValue(issueModal.getDetail());
//    newIssueRef.child("author").setValue(issueModal.getAuthor());
//
//    if (issueModal.getImageUri() != null) {
//        newIssueRef.child("imageUri").setValue(issueModal.getImageUri()).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()) {
//                    saveVideoUri(newIssueRef, issueModal, progressDialog);
//                } else {
//                    progressDialog.dismiss();
//                    Toast.makeText(getActivity(), "Failed to save image URI", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    } else {
//        saveVideoUri(newIssueRef, issueModal, progressDialog);
//    }
//}
//
//    private void saveVideoUri(DatabaseReference newIssueRef, IssueModal issueModal, ProgressDialog progressDialog) {
//        if (issueModal.getVideoUri() != null) {
//            newIssueRef.child("videoUri").setValue(issueModal.getVideoUri()).addOnCompleteListener(new OnCompleteListener<Void>() {
//                @Override
//                public void onComplete(@NonNull Task<Void> task) {
//                    if (task.isSuccessful()) {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "Issue posted successfully", Toast.LENGTH_SHORT).show();
//                        clearFields();
//                    } else {
//                        progressDialog.dismiss();
//                        Toast.makeText(getActivity(), "Failed to save video URI", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        } else {
//            progressDialog.dismiss();
//            Toast.makeText(getActivity(), "Issue posted successfully", Toast.LENGTH_SHORT).show();
//            clearFields();
//        }
//    }
//
//
//    private String getFileExtension(Uri uri) {
//        ContentResolver contentResolver = getActivity().getContentResolver();
//        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
//        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
//    }
//
//
//private void clearFields() {
//    titleField.setText("");
//    detailField.setText("");
//    //authorField.setText(""); // Remove this line or uncomment the line where you initialize authorField
//    imageField.setImageResource(R.drawable.cer);
//    videoView.setVideoURI(null);
//    imageUri = null;
//    videoUri = null;
//}
//
//}