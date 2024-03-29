package com.raf.cedaandreja.NotifikacioniServis.service;

import com.raf.cedaandreja.NotifikacioniServis.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;


public interface NotificationService {

    void sendActivationEmail(String recipientEmail, String activationLink);
    void sendLoginEmail(String recipientEmail, String activationLink);
    void sendPasswordChangeEmail(String recipientEmail, String activationLink);
    void sendReminderEmail(String recipientEmail, LocalDateTime vreme);
    void sendCancelEmail(String recipientEmailK, LocalDateTime vreme);
    void sendScheduleEmail(String recipientEmailK, LocalDateTime vreme);

    Page<Notification> findAllNotification(Pageable pageable);
    Page<Notification> findAllNotificationsByKorisnik(String email, Pageable pageable);

    Page<Notification> findAllNotificationsByKorisnikAndType(String korisnik, String type, Pageable pageable);

    Page<Notification> findAllNotificationsByType(String type, Pageable pageable);

    void sendUpdateEmail(String korisnik, String link);
}
