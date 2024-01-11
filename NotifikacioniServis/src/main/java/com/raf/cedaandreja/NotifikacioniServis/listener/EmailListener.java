package com.raf.cedaandreja.NotifikacioniServis.listener;

import com.raf.cedaandreja.NotifikacioniServis.Dto.NotificationDto;
import com.raf.cedaandreja.NotifikacioniServis.listener.helper.MessageHelper;
import com.raf.cedaandreja.NotifikacioniServis.service.NotificationService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@EnableAsync
@Component
public class EmailListener {
    private MessageHelper messageHelper;
    private NotificationService emailService;

    public EmailListener(MessageHelper messageHelper, NotificationService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @Async
    @JmsListener(destination = "notificationQueue", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        System.out.println(message);
        NotificationDto notificationDto = messageHelper.getMessage(message, NotificationDto.class);
        System.out.println("Stigo zahtev");
        switch (notificationDto.getType()){
            case "activation":{
                emailService.sendActivationEmail(notificationDto.getKorisnik(),notificationDto.getLink());
                break;
            }
            case "login":{
                emailService.sendLoginEmail(notificationDto.getKorisnik(),notificationDto.getLink());
                break;
            }
            case "update":{
                emailService.sendUpdateEmail(notificationDto.getKorisnik(),notificationDto.getLink());
                break;
            }
            case "passwordChange":{
                emailService.sendPasswordChangeEmail(notificationDto.getKorisnik(),notificationDto.getLink());
                break;
            }
            case "reminder":{
                emailService.sendReminderEmail(notificationDto.getKorisnik(), LocalDateTime.parse(notificationDto.getLink()));
                break;
            }
            case "cancel":{
                emailService.sendCancelEmail(notificationDto.getKorisnik(), LocalDateTime.parse(notificationDto.getLink()));
                break;
            }
            case "schedule":{
                emailService.sendScheduleEmail(notificationDto.getKorisnik(), LocalDateTime.parse(notificationDto.getLink()));
                break;
            }
        }
    }
}
