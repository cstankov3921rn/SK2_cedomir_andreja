package com.raf.cedaandreja.NotifikacioniServis.listener;

import com.raf.cedaandreja.NotifikacioniServis.listener.helper.MessageHelper;
import com.raf.cedaandreja.NotifikacioniServis.service.NotificationService;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class EmailListener {
//TODO idi u picku materinu moram da zamenim Controler sa ovim listenerem i da ubacim ovde
// switch kako bih mogo da razlikujem koja je poruka u pitanj. i takodje da naucim ovaj nacin
// pozivanja tojes postavljanja zahteva
    private MessageHelper messageHelper;
    private NotificationService emailService;

    public EmailListener(MessageHelper messageHelper, NotificationService emailService) {
        this.messageHelper = messageHelper;
        this.emailService = emailService;
    }

    @JmsListener(destination = "${destination.sendEmails}", concurrency = "5-10")
    public void addOrder(Message message) throws JMSException {
        //MatchesDto matchesDto = messageHelper.getMessage(message, MatchesDto.class);
        //emailService.sendSimpleMessage("nikolajr93og@gmail.com", "subject", matchesDto.toString());
    }
}
