package com.example.cervical_cancer;

public class EmailData {
    private String subject;
    private String body;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EmailData(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

}
