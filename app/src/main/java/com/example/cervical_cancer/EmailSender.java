package com.example.cervical_cancer;
// package com.example.cervical_cancer;
//


import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender implements Runnable {
    private List<FirebaseUser> users;
    private String subject = "This is a test email";
    private String body = "This is the body of the test email.";
    private Date time;

    public EmailSender(List<FirebaseUser> users, Date time) {
        this.users = users;
        this.time = time;
    }

    @Override
    public void run() {
        // Get the current date and set the time to 4:20 PM
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);
        calendar.set(Calendar.HOUR_OF_DAY, 16);
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);

        // Schedule the email sending task to run at the specified time every day
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com");
                props.put("mail.smtp.port", "587");
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");

                Session session = Session.getInstance(props, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("gokawkunda@gmail.com", "okwakunda002");
                    }
                });

                for (FirebaseUser user : users) {
                    try {
                        Message message = new MimeMessage(session);
                        Address senderAddress = new InternetAddress("gokawkunda@gmail.com");
                        Address recipientAddress = new InternetAddress(user.getEmail());

                        message.setFrom(senderAddress);
                        message.setRecipient(Message.RecipientType.TO, recipientAddress);
                        message.setSubject(subject);
                        message.setText(body);
                        message.setSentDate(new Date());

                        Transport.send(message);
                        System.out.println("Email sent to: " + user.getEmail());
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(task, calendar.getTime(), TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS));
    }

    public static void main(String[] args) {
        List<FirebaseUser> users = getUsers(); // Replace with your logic to fetch the users
        Date timeToSend = new Date(); // Create a new Date instance representing the current date and time
        EmailSender emailSender = new EmailSender(users, timeToSend);
        emailSender.run();
    }

    // Replace this method with your logic to fetch the users from Firebase
    private static List<FirebaseUser> getUsers() {
        List<FirebaseUser> users = new ArrayList<>();
        // Add your user-fetching logic here
        return users;
    }
}

//import com.google.firebase.auth.FirebaseUser;
//
//import java.util.Date;
//import java.util.List;
//import java.util.Properties;
//
//import javax.mail.Address;
//import javax.mail.Authenticator;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;
//
//public class EmailSender implements Runnable {
//
//    private List<FirebaseUser> users;
//    private String subject = "This is a test email";
//    private String body = "This is the body of the test email.";
//    private Date time = new Date();
//
//    public EmailSender(List<FirebaseUser> users) {
//        this.users = users;
//    }
//
//    @Override
//    public void run() {
//        Properties props = new Properties();
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(props, new Authenticator() {
//            @Override
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("gokwakunda@gmail.com", "okwakunda002");
//            }
//        });
//
//        for (FirebaseUser user : users) {
//            try {
//                Message message = new MimeMessage(session);
//                Address senderAddress = new InternetAddress("gokwakunda@gmail.com");
//                Address recipientAddress = new InternetAddress(user.getEmail());
//
//                message.setFrom(senderAddress);
//                message.setRecipient(Message.RecipientType.TO, recipientAddress);
//                message.setSubject(subject);
//                message.setText(body);
//                message.setSentDate(new Date(time.getTime() + ( 60 * 1000))); // One week later
//
//                Transport.send(message);
//            } catch (MessagingException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
