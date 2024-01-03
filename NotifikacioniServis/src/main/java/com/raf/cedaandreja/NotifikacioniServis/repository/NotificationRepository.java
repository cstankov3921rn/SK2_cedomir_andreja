package com.raf.cedaandreja.NotifikacioniServis.repository;

import com.raf.cedaandreja.NotifikacioniServis.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

}
