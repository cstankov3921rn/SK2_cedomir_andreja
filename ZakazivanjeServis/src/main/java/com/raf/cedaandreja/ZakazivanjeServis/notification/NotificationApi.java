package com.raf.cedaandreja.ZakazivanjeServis.notification;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raf.cedaandreja.ZakazivanjeServis.dto.NotificationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;


@Component
public class NotificationApi {


    @Autowired
    private final JmsTemplate jmsTemplate;
    private MessageHelper messageHelper;

    public NotificationApi(JmsTemplate jmsTemplate, MessageHelper messageHelper) {
        this.jmsTemplate = jmsTemplate;
        this.messageHelper = messageHelper;
    }

    public void sendNotification(NotificationDto notificationDto) {
        jmsTemplate.convertAndSend("notificationQueue",messageHelper.createTextMessage(notificationDto));

    }

    private String convertObjectToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
}
