package com.example.cervical_cancer.modals;

public class CommentModal {
    private String profile; // Updated to store the URL of the user's profile picture
    private String issueID;
    private String comment;
    private String username; // Updated to store the user's username

    public CommentModal() {
        // Default constructor required for calls to DataSnapshot.getValue(CommentModal.class)
    }

    public CommentModal(String profile, String issueID, String comment, String username) {
        this.profile = profile;
        this.issueID = issueID;
        this.comment = comment;
        this.username = username;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getIssueID() {
        return issueID;
    }

    public void setIssueID(String issueID) {
        this.issueID = issueID;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getAuthor() {
        return username;
    }

    public void setAuthor(String username) {
        this.username = username;
    }
}
