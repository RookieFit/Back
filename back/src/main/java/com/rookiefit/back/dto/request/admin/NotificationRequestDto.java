package com.rookiefit.back.dto.request.admin;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotificationRequestDto {
    private String token;
    private Long notificationId;
    private String notificationAuthor;
    private String notificationContent;
    private String notificationTitle;
    private LocalDateTime notificationCreatedTime;
    private Boolean notificationIsModified;
    private String notificationImageUri;
    private Boolean notificationBoardDown;
    private LocalDateTime notificationBoardUpdatedTime;
}
