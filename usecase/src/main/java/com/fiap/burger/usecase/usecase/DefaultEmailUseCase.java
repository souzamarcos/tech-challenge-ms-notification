package com.fiap.burger.usecase.usecase;

import com.fiap.burger.usecase.adapter.usecase.EmailUseCase;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Properties;


public class DefaultEmailUseCase implements EmailUseCase {

    private final Logger LOGGER = LoggerFactory.getLogger(DefaultEmailUseCase.class);

    @Value("${smtp.host}")
    private String host;
    @Value("${smtp.username}")
    private String username;

    @Value("${smtp.password}")
    private String password;

    @Override
    public void sendEmail(String to, String subject, String messageText) {
        //provide sender's to ID
        String from = "example@tech-challenge.com";
        //configure Mailtrap's SMTP server details
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        //create the Session object
        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            //create a MimeMessage object
            Message message = new MimeMessage(session);
            //set From to field
            message.setFrom(new InternetAddress(from));
            //set To to field
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //set to subject field
            message.setSubject(subject);
            //set the content of the to messageText
            message.setText(messageText);
            //send the to messageText
            Transport.send(message);
            LOGGER.info("Email Message Sent Successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
