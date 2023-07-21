package com.example.cervical_cancer.modals;
import com.google.gson.annotations.SerializedName;

public class Video {
    @SerializedName("videoId")
    private String videoId;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("thumbnail")
    private String thumbnail;

    // Constructor (if needed)

    // Getter methods
    public String getVideoId() {
        return videoId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}

