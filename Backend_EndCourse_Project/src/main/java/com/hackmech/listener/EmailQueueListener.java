package com.hackmech.listener;

import com.hackmech.config.RabbitMQConfig;
import com.hackmech.dto.EmailMessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailQueueListener {

    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = RabbitMQConfig.EMAIL_QUEUE)
    public void receiveEmailMessage(EmailMessageDTO message) {
        System.out.println("✅ Received Email Message:");
        System.out.println("To: " + message.getTo());
        System.out.println("Subject: " + message.getSubject());
        System.out.println("Body: " + message.getBody());

        // 🔔 Send the actual email
        sendEmail(message);
    }

    private void sendEmail(EmailMessageDTO message) {
        try {
            SimpleMailMessage email = new SimpleMailMessage();
            email.setTo(message.getTo());
            email.setSubject(message.getSubject());
            email.setText(message.getBody());

            mailSender.send(email);
            System.out.println("Email sent successfully to " + message.getTo());
        } catch (Exception e) {
            System.err.println("Failed to send email: " + e.getMessage());
        }
    }
}
