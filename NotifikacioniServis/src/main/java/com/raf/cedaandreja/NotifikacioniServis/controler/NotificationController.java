package com.raf.cedaandreja.NotifikacioniServis.controler;

import com.raf.cedaandreja.NotifikacioniServis.domain.Notification;
import com.raf.cedaandreja.NotifikacioniServis.service.NotificationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private NotificationService notificationService;


        public NotificationController(NotificationService notificationService) {
            this.notificationService = notificationService;
        }

        @PostMapping("/activation-email")
        public void sendActivationEmail(@RequestParam String email, @RequestParam String activationLink) {
            notificationService.sendActivationEmail(email, activationLink);
        }
        @PostMapping("/login-email")
        public void sendLoginEmail(@RequestParam String email, @RequestParam String activationLink) {
            notificationService.sendLoginEmail(email, activationLink);
        }
        @PostMapping("/password-reset-email")
        public void sendPasswordResetEmail(@RequestParam String email, @RequestParam String activationLink) {
            notificationService.sendPasswordChangeEmail(email, activationLink);
        }
        @PostMapping("/remind-email")
        public void sendDanPreEmail(@RequestParam String email, @RequestParam LocalDateTime vreme) {
            notificationService.sendReminderEmail(email, vreme);
        }
        @PostMapping("/cancel-email")
        public void sendOtkazivanjeEmail(@RequestParam String emailK, @RequestParam String emailM, @RequestParam LocalDateTime vreme) {
            notificationService.sendCancelEmail(emailK, emailM, vreme);
        }
        @PostMapping("/schedule-email")
        public void sendZakazivanjeEmail(@RequestParam String emailK,@RequestParam String emailM, @RequestParam LocalDateTime vreme) {
        notificationService.sendScheduleEmail(emailK,emailM, vreme);
        }

    @GetMapping("/all")
    public ResponseEntity<Page<Notification>> getAll( Pageable pageable) {
        return new ResponseEntity<>(notificationService.findAllNotification(pageable), HttpStatus.OK);
    }

}
