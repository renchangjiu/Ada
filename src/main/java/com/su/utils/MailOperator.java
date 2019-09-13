package com.su.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class MailOperator {


    private final JavaMailSender mailSender;

    @Autowired
    public MailOperator(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }


    public void sendSimpleMail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("muzhez@163.com");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }

    // 异步
    @Async
    public void sendSimpleMailAsync(String to, String subject, String text) {
        sendSimpleMail(to, subject, text);
    }


}
