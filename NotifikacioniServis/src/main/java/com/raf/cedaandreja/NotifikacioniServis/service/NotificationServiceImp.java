package com.raf.cedaandreja.NotifikacioniServis.service;

import com.raf.cedaandreja.NotifikacioniServis.domain.Notification;
import com.raf.cedaandreja.NotifikacioniServis.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Component
public class NotificationServiceImp implements NotificationService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private NotificationRepository notificationRepository;

    public NotificationServiceImp(JavaMailSender mailSender, NotificationRepository notificationRepository) {
        this.mailSender = mailSender;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public void sendActivationEmail(String recipientEmail, String activationLink) {
        // Logika za slanje aktivacionog e-maila
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Aktivacija naloga");
        mailMessage.setText("Pozdrav, da biste se verifikovali idite na sledeći link: " + activationLink);
        mailSender.send(mailMessage);

        // Čuvanje poslate notifikacije
        Notification notification = new Notification();
        notification.setRecipientEmail(recipientEmail);
        notification.setNotificationType("Aktivacioni e-mail");
        notification.setParameters("Activation Link: " + activationLink);
        notificationRepository.save(notification);
    }
    @Override
    public void sendLoginEmail(String recipientEmail, String activationLink) {
        // Logika za slanje aktivacionog e-maila
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Potvrda login-a");
        mailMessage.setText("Pozdrav, da biste potvrdili login idite na sledeći link: " + activationLink);
        mailSender.send(mailMessage);

        // Čuvanje poslate notifikacije
        Notification notification = new Notification();
        notification.setRecipientEmail(recipientEmail);
        notification.setNotificationType("Login e-mail");
        notification.setParameters("Login Link: " + activationLink);
        notificationRepository.save(notification);
    }

    @Override
    public void sendPasswordChangeEmail(String recipientEmail, String activationLink) {
        // Logika za slanje aktivacionog e-maila
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Potvrda promene sifre");
        mailMessage.setText("Pozdrav, da biste potvrdili promenu sifre idite na sledeći link: " + activationLink);
        mailSender.send(mailMessage);

        // Čuvanje poslate notifikacije
        Notification notification = new Notification();
        notification.setRecipientEmail(recipientEmail);
        notification.setNotificationType("Promena sifre");
        notification.setParameters("Promena sifre Link: " + activationLink);
        notificationRepository.save(notification);
    }

    @Override
    public void sendReminderEmail(String recipientEmail, LocalDateTime vreme) {
        // Logika za slanje aktivacionog e-maila
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmail);
        mailMessage.setSubject("Potvrda promene sifre");
        mailMessage.setText("Pozdrav, podsetnik da imate trening " + vreme);
        mailSender.send(mailMessage);

        // Čuvanje poslate notifikacije
        Notification notification = new Notification();
        notification.setRecipientEmail(recipientEmail);
        notification.setNotificationType("Podsetnik");
        notification.setParameters("Vreme treninga: " + vreme);
        notificationRepository.save(notification);
    }

    @Override
    public void sendCancelEmail(String recipientEmailK,String recipientEmailM, LocalDateTime vreme) {
        // Logika za slanje aktivacionog e-maila
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmailK);
        mailMessage.setSubject("Potvrda otkazivanja treninga");
        mailMessage.setText("Pozdrav, ovo je potvrda da ste uspesno otkazali trening " + vreme);
        mailSender.send(mailMessage);
        //za menadjera
        SimpleMailMessage mailMessage1 = new SimpleMailMessage();
        mailMessage1.setTo(recipientEmailM);
        mailMessage1.setSubject("Potvrda zakazivanja treninga");
        mailMessage1.setText("Pozdrav, ovo je potvrda da se je neko odkazao prijavu za trening " + vreme);
        mailSender.send(mailMessage1);

        // Čuvanje poslate notifikacije
        Notification notification = new Notification();
        notification.setRecipientEmail(recipientEmailK);
        notification.setNotificationType("Otkazivanje");
        notification.setParameters("Vreme treninga: " + vreme);
        notificationRepository.save(notification);
    }

    @Override
    public void sendScheduleEmail(String recipientEmailK, String recipientEmailM, LocalDateTime vreme) {
        // Logika za slanje aktivacionog e-maila
        //za klijenta
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmailK);
        mailMessage.setSubject("Potvrda zakazivanja treninga");
        mailMessage.setText("Pozdrav, ovo je potvrda da ste uspesno zakazali trening " + vreme);
        mailSender.send(mailMessage);
        //za menadjera
        SimpleMailMessage mailMessage1 = new SimpleMailMessage();
        mailMessage1.setTo(recipientEmailM);
        mailMessage1.setSubject("Potvrda zakazivanja treninga");
        mailMessage1.setText("Pozdrav, ovo je potvrda da se jos neko prijavio na trening " + vreme);
        mailSender.send(mailMessage1);

        // Čuvanje poslate notifikacije
        Notification notification = new Notification();
        notification.setRecipientEmail(recipientEmailK);
        notification.setNotificationType("Zakazivanje");
        notification.setParameters("Vreme treninga: " + vreme);
        notificationRepository.save(notification);
    }

    @Override
    public Page<Notification> findAllNotification(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }
}
