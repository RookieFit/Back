package com.rookiefit.back.entity;

import java.time.LocalDateTime;

import com.rookiefit.back.dto.request.admin.NotificationRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long notificationId;

    @Column(nullable = false)
    private String notificationAuthor;

    private String notificationContent;

    @Column(nullable = false)
    private String notificationTitle;

    @Column(nullable = false)
    private LocalDateTime notificationCreatedTime;

    @Column(nullable = false)
    private Boolean notificationIsModified = false;

    private String notificationImageUri;

    @Column(nullable = false)
    private Boolean notificationBoardDown = false;

    private LocalDateTime notificationBoardUpdatedTime;

    public NotificationEntity(NotificationRequestDto dto, String currentUserId) {
        this.notificationAuthor = currentUserId;
        this.notificationContent = dto.getNotificationContent();
        this.notificationTitle = dto.getNotificationTitle();
        this.notificationCreatedTime = dto.getNotificationCreatedTime()!=null ? dto.getNotificationCreatedTime():LocalDateTime.now();
        this.notificationImageUri = dto.getNotificationImageUri();
    }
}
