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
    @JmsListener(destination = "${destination.sendEmails}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        NotificationDto notificationDto = messageHelper.getMessage(message, NotificationDto.class);
        switch (notificationDto.getType()){
            case "activation":{
                emailService.sendActivationEmail(notificationDto.getKorisnik(),notificationDto.getExtra().get("link"));
                break;
            }
            case "login":{
                emailService.sendLoginEmail(notificationDto.getKorisnik(),notificationDto.getExtra().get("link"));
                break;
            }
            case "passwordChange":{
                emailService.sendPasswordChangeEmail(notificationDto.getKorisnik(),notificationDto.getExtra().get("link"));
                break;
            }
            case "reminder":{
                emailService.sendReminderEmail(notificationDto.getKorisnik(), LocalDateTime.parse(notificationDto.getExtra().get("vreme")));
                break;
            }
            case "cancel":{
                emailService.sendCancelEmail(notificationDto.getKorisnik(),notificationDto.getExtra().get("menagerEmail"), LocalDateTime.parse(notificationDto.getExtra().get("vreme")));
                break;
            }
            case "schedule":{
                emailService.sendScheduleEmail(notificationDto.getKorisnik(),notificationDto.getExtra().get("menagerEmail"), LocalDateTime.parse(notificationDto.getExtra().get("vreme")));
                break;
            }
        }
    }
}
