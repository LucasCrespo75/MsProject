package com.br.email.service;

import com.br.email.enums.StatusEmail;
import com.br.email.model.EmailModel;
import com.br.email.repository.EmailRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class EmailService {

    final EmailRepository emailRepository;
    final JavaMailSender javaMailSender;


    public EmailService(EmailRepository emailRepository, JavaMailSender javaMailSender) {
        this.emailRepository = emailRepository;
        this.javaMailSender = javaMailSender;
    }


    @Value("${spring.mail.username}")
    private String emailFrom;


    @Transactional
    public EmailModel sendEmail(EmailModel emailModel) {
        try {
            emailModel.setSendDataEmail(LocalDateTime.now());
            emailModel.setEmailFrom(emailFrom);

            SimpleMailMessage messange = new SimpleMailMessage();
            messange.setTo(emailModel.getEmailTo());
            messange.setSubject(emailModel.getSubject());
            messange.setText(emailModel.getText());
            javaMailSender.send(messange);

            emailModel.setEmailStatus(StatusEmail.SENT);
        } catch (MailException e) {
            emailModel.setEmailStatus(StatusEmail.ERROR);

        } finally {
            return emailRepository.save(emailModel);

        }
    }
}
