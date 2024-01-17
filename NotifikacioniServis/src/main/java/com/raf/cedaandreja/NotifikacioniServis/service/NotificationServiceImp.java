package com.raf.cedaandreja.NotifikacioniServis.service;

import com.raf.cedaandreja.NotifikacioniServis.domain.Notification;
import com.raf.cedaandreja.NotifikacioniServis.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

@EnableAsync
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
    @Async
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
        notification.setKorisnik(recipientEmail);
        notification.setType("Aktivacioni e-mail");
        notification.setParameters("Activation Link: " + activationLink);
        notificationRepository.save(notification);
    }
    @Async
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
        notification.setKorisnik(recipientEmail);
        notification.setType("Login e-mail");
        notification.setParameters("Login Link: " + activationLink);
        notificationRepository.save(notification);
    }
    @Async
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
        notification.setKorisnik(recipientEmail);
        notification.setType("Promena sifre");
        notification.setParameters("Promena sifre Link: " + activationLink);
        notificationRepository.save(notification);
    }
    @Async
    @Override
    public void sendReminderEmail(String recipientEmail, LocalDateTime vreme) {



        Timer timer = new Timer();

        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                // Logika za slanje aktivacionog e-maila
                SimpleMailMessage mailMessage = new SimpleMailMessage();
                mailMessage.setTo(recipientEmail);
                mailMessage.setSubject("Potvrda promene sifre");
                mailMessage.setText("Pozdrav, podsetnik da imate trening " + vreme);
                mailSender.send(mailMessage);

                // Čuvanje poslate notifikacije
                Notification notification = new Notification();
                notification.setKorisnik(recipientEmail);
                notification.setType("Podsetnik");
                notification.setParameters("Vreme treninga: " + vreme);
                notificationRepository.save(notification);

                System.out.println("Akcija se izvršava na datum: " + vreme);
            }
        };
        Date d = new Date();
        d.setYear(vreme.getYear());
        d.setMonth(vreme.getMonthValue());
        d.setDate(vreme.getDayOfMonth()-1);
        timer.schedule(task,d);
    }
    @Async
    @Override
    public void sendCancelEmail(String recipientEmailK, LocalDateTime vreme) {
        // Logika za slanje aktivacionog e-maila
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmailK);
        mailMessage.setSubject("Potvrda otkazivanja treninga");
        mailMessage.setText("Pozdrav, ovo je potvrda da ste uspesno otkazali trening " + vreme);
        mailSender.send(mailMessage);


        // Čuvanje poslate notifikacije
        Notification notification = new Notification();
        notification.setKorisnik(recipientEmailK);
        notification.setType("Otkazivanje");
        notification.setParameters("Vreme treninga: " + vreme);
        notificationRepository.save(notification);


    }
    @Async
    @Override
    public void sendScheduleEmail(String recipientEmailK, LocalDateTime vreme) {
        // Logika za slanje aktivacionog e-maila
        //za klijenta
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipientEmailK);
        mailMessage.setSubject("Potvrda zakazivanja treninga");
        mailMessage.setText("Pozdrav, ovo je potvrda da ste uspesno zakazali trening " + vreme);
        mailSender.send(mailMessage);


        // Čuvanje poslate notifikacije
        Notification notification = new Notification();
        notification.setKorisnik(recipientEmailK);
        notification.setType("Zakazivanje");
        notification.setParameters("Vreme treninga: " + vreme);
        notificationRepository.save(notification);

    }

    @Override
    public Page<Notification> findAllNotification(Pageable pageable) {
        return notificationRepository.findAll(pageable);
    }

    @Override
    public Page<Notification> findAllNotificationsByType(String type, Pageable pageable) {
        return notificationRepository.findAllNotificationsByType(pageable,type);
    }

    @Override
    public Page<Notification> findAllNotificationsByKorisnik(String email, Pageable pageable) {
        return notificationRepository.findAllNotificationsByKorisnik(pageable,email);
    }

    @Override
    public Page<Notification> findAllNotificationsByKorisnikAndType(String korisnik, String type, Pageable pageable) {
        return notificationRepository.findAllNotificationsByKorisnikAndType(pageable,korisnik,type);
    }

    @Override
    public void sendUpdateEmail(String korisnik, String link) {
        // Logika za slanje aktivacionog e-maila
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(korisnik);
        mailMessage.setSubject("Potvrda izmena profila");
        mailMessage.setText("Pozdrav, ovo je potvrda da ste uspesno izmenili svoj profil link: " + link);
        mailSender.send(mailMessage);

        // Čuvanje poslate notifikacije
        Notification notification = new Notification();
        notification.setKorisnik(korisnik);
        notification.setType("Izmena profila");
        notification.setParameters("Potvrda izmene profila Link: " + link);
        notificationRepository.save(notification);
    }
}
