package com.raf.cedaandreja.NotifikacioniServis.listener.helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.cedaandreja.NotifikacioniServis.Dto.NotificationDto;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.TextMessage;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MessageHelper {

    private Validator validator;
    private ObjectMapper objectMapper;
    private JmsTemplate jmsTemplate;

    public MessageHelper(Validator validator, ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.validator = validator;
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    public <T> T getMessage(Message message, Class<T> clazz) throws RuntimeException, JMSException {
        String json = ((TextMessage) message).getText();
        System.out.println(json);

        // Deserijalizuj JSON u objekat NotificationDto
        try {
            NotificationDto notificationDto = objectMapper.readValue(json, NotificationDto.class);
            return (T) notificationDto;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }

    public String createTextMessage(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Problem with creating text message");
        }
    }

    private <T> void printViolationsAndThrowException(Set<ConstraintViolation<T>> violations) {
        String concatenatedViolations = violations.stream()
                .map(ConstraintViolation::getMessage)
                .collect(Collectors.joining(", "));
        throw new RuntimeException(concatenatedViolations);
    }
}

