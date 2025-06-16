// EmailSenderService.java
package com.hackmech.service;

import com.hackmech.config.RabbitMQConfig;
import com.hackmech.dto.EmailMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {

    private final RabbitTemplate rabbitTemplate;

    public EmailSenderService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void queueEmail(String to, String subject, String body) {
        EmailMessageDTO message = new EmailMessageDTO(to, subject, body);
        rabbitTemplate.convertAndSend(RabbitMQConfig.EMAIL_QUEUE, message);
    }
}
