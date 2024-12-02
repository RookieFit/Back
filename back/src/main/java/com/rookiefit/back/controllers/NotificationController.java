package com.rookiefit.back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.notification.NotificationRequestDto;
import com.rookiefit.back.dto.response.notification.InputNotificationResponseDto;
import com.rookiefit.back.service.NotificationService;
import com.rookiefit.back.dto.response.notification.DeleteNotificationResponseDto;
import com.rookiefit.back.dto.response.notification.GetNotificationListResponseDto;
import com.rookiefit.back.dto.response.notification.GetNotificationResponseDto;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1")
@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @PostMapping("/admin/create-notification")
    public ResponseEntity<? super InputNotificationResponseDto> createNotification(
        @RequestBody @Valid NotificationRequestDto dto) {
            ResponseEntity<? super InputNotificationResponseDto> responseBody = notificationService.createNotification(dto);
            return responseBody;
    }

    @PutMapping("/admin/update-notification/{id}")
    public ResponseEntity<? super InputNotificationResponseDto> updateNotification(
        @PathVariable("id")Long notificationId, @RequestBody @Valid NotificationRequestDto dto) {
            ResponseEntity<? super InputNotificationResponseDto> responseBody = notificationService.updateNotification(dto, notificationId);
            return responseBody;
    }

    @GetMapping("/get-all-notification")
    public ResponseEntity<? super GetNotificationListResponseDto> getAllNotification() {
        ResponseEntity<? super GetNotificationListResponseDto> responseBody = notificationService.getAllNotification();
        return responseBody;
    }

    @GetMapping("/get-notification/{id}")
    public ResponseEntity<? super GetNotificationResponseDto> getNotification(
        @PathVariable("id") Long notificationId) {
        ResponseEntity<? super GetNotificationResponseDto> responseBody = notificationService.getNotification(notificationId);
        return responseBody;
    }

    @GetMapping("/search-notification")
    public ResponseEntity<? super GetNotificationListResponseDto> searchNotification(
        @RequestParam(required = false, value = "keyword") String keyword,
        @RequestParam(required = false, value = "field") String field) {
        ResponseEntity<? super GetNotificationListResponseDto> responseBody = notificationService.searchNotification(keyword, field);
        return responseBody;
    }

    @DeleteMapping("/admin/delete-notification/{id}")
    public ResponseEntity<? super DeleteNotificationResponseDto> deleteNotification(
        @PathVariable("id") Long notificationId) {
        ResponseEntity<? super DeleteNotificationResponseDto> responseBody = notificationService.deleteNotification(notificationId);
        return responseBody;
    }
}