package com.raf.cedaandreja.NotifikacioniServis.repository;

import com.raf.cedaandreja.NotifikacioniServis.domain.Notification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
//TODO Da se doda opcija kad se zove svaka od ovih da se takodje filtrira po vremenskim granicama koje su postavljene
    Page<Notification> findAllNotificationsByKorisnik(Pageable pageable, String korisnik);

    Page<Notification> findAllNotificationsByKorisnikAndType(Pageable pageable, String korisnik, String type);

    Page<Notification> findAllNotificationsByType(Pageable pageable, String type);
}
