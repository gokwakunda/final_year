package com.example.cervical_cancer.modals;

public class Message {
    private String sender;
    private String receiver;
    private String context;
    private long timestamp;

    public Message() {
        // Default constructor required for Firebase
    }

    public Message(String sender, String receiver, String context) {
        this.sender = sender;
        this.receiver = receiver;
        this.context = context;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
