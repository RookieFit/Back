package com.rookiefit.back.dto.response.notification;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.NotificationEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetNotificationResponseDto extends ResponseDto{

    private Long notificationId;
    private String notificationAuthor;
    private String notificationContent;
    private String notificationTitle;
    private LocalDateTime notificationCreatedTime;
    private Boolean notificationIsModified;
    private String notificationImageUri;
    private Boolean notificationBoardDown;
    private LocalDateTime notificationBoardUpdatedTime;

    public GetNotificationResponseDto(NotificationEntity notificationEntity) {
        this.notificationId = notificationEntity.getNotificationId();
        this.notificationAuthor = notificationEntity.getNotificationAuthor();
        this.notificationContent = notificationEntity.getNotificationContent();
        this.notificationTitle = notificationEntity.getNotificationTitle();
        this.notificationCreatedTime = notificationEntity.getNotificationCreatedTime();
        this.notificationIsModified = notificationEntity.getNotificationIsModified();
        this.notificationImageUri = notificationEntity.getNotificationImageUri();
        this.notificationBoardDown = notificationEntity.getNotificationBoardDown();
        this.notificationBoardUpdatedTime = notificationEntity.getNotificationBoardUpdatedTime();
    }

     public static ResponseEntity<? super GetNotificationResponseDto> success(NotificationEntity notificationEntitiy) {
        GetNotificationResponseDto responseBody = new GetNotificationResponseDto(notificationEntitiy);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
