
        package com.example.cervical_cancer.modals;

public class IssueModal {
    private String id;
    private String title;
    private String detail;
    private String author;
    private String profile;
    private String imageUri;
    private String videoUri;

    public IssueModal() {
        // Default constructor required for calls to DataSnapshot.getValue(IssueModal.class)
    }

    public IssueModal(String id,String title, String detail) {
        this.title = title;
        this.detail = detail;
    }

    public IssueModal(String id,String title, String detail, String author, String profile) {
        this.title = title;
        this.detail = detail;
        this.profile = profile;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public String getVideoUri() {
        return videoUri;
    }

    public void setVideoUri(String videoUri) {
        this.videoUri = videoUri;
    }
}

//package com.example.cervical_cancer.modals;
//
//import android.net.Uri;
//
//public class IssueModal {
//    private String id;
//    private String title;
//    private String detail;
//    private String author;
//    private String profile;
//    private String imageUri;
//    private String videoUri;
//
////    private String videoUri;
//
//
//    public IssueModal() {
//        // Default constructor required for calls to DataSnapshot.getValue(IssueModal.class)
//    }
//
//    public IssueModal(String title, String detail) {
//        this.title = title;
//        this.detail = detail;
//    }
//
//    public IssueModal(String title, String detail, String author, Uri profile) {
//        this.title = title;
//        this.detail = detail;
//        this.profile = String.valueOf(profile);
//        this.author = author;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDetail() {
//        return detail;
//    }
//
//    public void setDetail(String detail) {
//        this.detail = detail;
//    }
//
//    public String getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(String author) {
//        this.author = author;
//    }
//
//    public String getProfile() {
//        return profile;
//    }
//
//    public void setProfile(Uri profile) {
//        this.profile = String.valueOf(profile);
//    }
//
//    public String getImageUri() {
//        return imageUri;
//    }
//
//    public void setImageUri(String imageUri) {
//        this.imageUri = imageUri;
//    }
//
//    public String getVideoUri() {
//        return videoUri;
//    }
//
//    public void setVideoUri(String videoUri) {
//        this.videoUri = videoUri;
//    }
//}