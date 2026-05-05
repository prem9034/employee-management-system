package com.springcrud1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendLeaveApprovedMail(String to, String start, String end) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("Leave Approved");
        message.setText("Your leave from " + start + " to " + end + " has been approved.");

        mailSender.send(message);
    }
}