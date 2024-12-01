package com.rookiefit.back.dto.response.notification;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.NotificationEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetNotificationListResponseDto extends ResponseDto{

    private Long notificationId;
    private String notificationAuthor;
    private String notificationContent;
    private String notificationTitle;
    private LocalDateTime notificationCreatedTime;

    private GetNotificationListResponseDto(NotificationEntity notificationEntity) {
        this.notificationId = notificationEntity.getNotificationId();
        this.notificationAuthor = notificationEntity.getNotificationAuthor();
        this.notificationContent = notificationEntity.getNotificationContent();
        this.notificationTitle = notificationEntity.getNotificationTitle();
        this.notificationCreatedTime = notificationEntity.getNotificationCreatedTime();
    }

    //공지게시글 가져오기(241201_김민준)
    public static ResponseEntity<? super GetNotificationListResponseDto> success(List<NotificationEntity> notificationEntities) {
        List<GetNotificationListResponseDto> responseBody = notificationEntities.stream()
            .map(GetNotificationListResponseDto::new) // DTO 생성자를 이용해 변환
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
