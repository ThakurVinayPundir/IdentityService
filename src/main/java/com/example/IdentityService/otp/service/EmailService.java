package com.example.IdentityService.otp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailService {
   @Autowired
   private JavaMailSender javaMailSender;

   public void sendOtpMessage(String to, String subject, String message)  {

      MimeMessage msg = javaMailSender.createMimeMessage();

      MimeMessageHelper helper = null;
      try {
         helper = new MimeMessageHelper(msg, true);
         helper.setTo(to);
         helper.setSubject(subject);
         helper.setText(message, true);
         javaMailSender.send(msg);
      } catch (MessagingException e) {
         e.printStackTrace();
      }

   }

   @Async
   public void sendEmail(SimpleMailMessage email) {
      javaMailSender.send(email);
   }
}
