// package com.example.demo.service;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.mail.SimpleMailMessage;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.stereotype.Service;
// import java.util.Random;

// @Service
// public class EmailSender {
    

//     @Autowired
//     private JavaMailSender mailSender;

//     public int sendVerificationEmail(String toEmail) {
//         int code = new Random().nextInt(900000) + 100000; // 6-digit code

//         SimpleMailMessage message = new SimpleMailMessage();
//         message.setFrom("your-email@gmail.com");
//         message.setTo(toEmail);
//         message.setSubject("Your Verification Code");
//         message.setText("Your verification code is: " + code);

//         mailSender.send(message);

//         return code; // Save this in DB or cache for later verification
//     }
// }


