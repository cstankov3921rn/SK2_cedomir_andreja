package com.raf.cedaandreja.NotifikacioniServis.service;

import com.raf.cedaandreja.NotifikacioniServis.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


public interface NotificationService {

    void sendActivationEmail(String recipientEmail, String activationLink);
    void sendLoginEmail(String recipientEmail, String activationLink);
    void sendPasswordChangeEmail(String recipientEmail, String activationLink);
    void sendReminderEmail(String recipientEmail, LocalDateTime vreme);
    void sendCancelEmail(String recipientEmailK,String recipientEmailM, LocalDateTime vreme);
    void sendScheduleEmail(String recipientEmailK, String recipientEmailM, LocalDateTime vreme);

    Page<Notification> findAllNotification(Pageable pageable);
}
