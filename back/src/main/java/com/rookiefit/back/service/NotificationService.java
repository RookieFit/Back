package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.admin.NotificationRequestDto;
import com.rookiefit.back.dto.response.admin.CreateNotificationResponseDto;

public interface NotificationService {
    ResponseEntity<? super CreateNotificationResponseDto> createNotification(NotificationRequestDto dto);
}
