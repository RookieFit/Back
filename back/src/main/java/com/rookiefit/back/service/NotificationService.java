package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.notification.NotificationRequestDto;
import com.rookiefit.back.dto.response.notification.InputNotificationResponseDto;
import com.rookiefit.back.dto.response.notification.DeleteNotificationResponseDto;
import com.rookiefit.back.dto.response.notification.GetNotificationListResponseDto;
import com.rookiefit.back.dto.response.notification.GetNotificationResponseDto;

public interface NotificationService {
    ResponseEntity<? super InputNotificationResponseDto> createNotification(NotificationRequestDto dto, String currentUserId);
    ResponseEntity<? super InputNotificationResponseDto> updateNotification(NotificationRequestDto dto, Long notificationId, String currentUserId);
    ResponseEntity<? super GetNotificationListResponseDto> getAllNotification();
    ResponseEntity<? super GetNotificationResponseDto> getNotification(Long id);
    ResponseEntity<? super GetNotificationListResponseDto> searchNotification(String keyword, String field);
    ResponseEntity<? super DeleteNotificationResponseDto> deleteNotification(Long notificationId);
}
