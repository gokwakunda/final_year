package com.example.cervical_cancer.modals;

public class User {
    private String username;
    private String email;
    private String profilePicture;
    private boolean hasUnreadMessages;

    public User() {
        // Default constructor required for Firebase
    }

    public User(String username, String email, String profilePicture) {
        this.username = username;
        this.email = email;
        this.profilePicture = profilePicture;
        this.hasUnreadMessages = false; // Initialize with no unread messages
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public boolean hasUnreadMessages() {
        return hasUnreadMessages;
    }

    public void setHasUnreadMessages(boolean hasUnreadMessages) {
        this.hasUnreadMessages = hasUnreadMessages;
    }
}

//
//public class User {
//    private String username;
//    private String email;
//    private String profilePicture;
//    private boolean hasUnreadMessages;
//
//    public User() {
//        // Default constructor required for Firebase
//    }
//
//    public User(String username, String email, String profilePicture) {
//        this.username = username;
//        this.email = email;
//        this.profilePicture = profilePicture;
//        this.hasUnreadMessages = false; // Initialize with no unread messages
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }
//
//    public String getProfilePicture() {
//        return profilePicture;
//    }
//
//    public void setProfilePicture(String profilePicture) {
//        this.profilePicture = profilePicture;
//    }
//
//    public boolean hasUnreadMessages() {
//        return hasUnreadMessages;
//    }
//
//    public void setHasUnreadMessages(boolean hasUnreadMessages) {
//        this.hasUnreadMessages = hasUnreadMessages;
//    }
//}

