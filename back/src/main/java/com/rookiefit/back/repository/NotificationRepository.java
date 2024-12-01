package com.rookiefit.back.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.NotificationEntity;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long>{
    List<NotificationEntity> findByNotificationTitleContaining(String keyword);
    List<NotificationEntity> findByNotificationContentContaining(String keyword);
    List<NotificationEntity> findByNotificationAuthorContaining(String keyword);
}
