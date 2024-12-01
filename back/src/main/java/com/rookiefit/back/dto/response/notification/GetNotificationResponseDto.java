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

    public GetNotificationResponseDto(NotificationEntity notificationEntity) {
        this.notificationId = notificationEntity.getNotificationId();
        this.notificationAuthor = notificationEntity.getNotificationAuthor();
        this.notificationContent = notificationEntity.getNotificationContent();
        this.notificationTitle = notificationEntity.getNotificationTitle();
        this.notificationCreatedTime = notificationEntity.getNotificationCreatedTime();
    }

     public static ResponseEntity<? super GetNotificationResponseDto> success(NotificationEntity notificationEntitiy) {
        GetNotificationResponseDto responseBody = new GetNotificationResponseDto(notificationEntitiy);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
