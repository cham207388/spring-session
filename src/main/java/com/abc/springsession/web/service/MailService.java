package com.abc.springsession.web.service;

import com.abc.springsession.exception.Message;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String email, boolean send) {
        if (send) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email.trim());
            message.setSubject(Message.EMAIL_SUBJECT.getValue());
            message.setText(Message.EMAIL_TEXT.getValue());

            mailSender.send(message);
        }
    }
}
