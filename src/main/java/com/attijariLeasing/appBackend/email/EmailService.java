package com.attijariLeasing.appBackend.email;

import com.attijariLeasing.appBackend.ApiResponse.Response;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;


    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public Response sendEmail(Email email){
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(email.getFrom());
        message.setTo(email.getTo());
        message.setSubject(email.getSubject());
        message.setText(email.getBody());

        this.mailSender.send(message);
        return new Response("Operation envoyer est terminer avec succès", true);
    }
}
