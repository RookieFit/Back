package com.rookiefit.back.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.notification.NotificationRequestDto;
import com.rookiefit.back.dto.response.notification.InputNotificationResponseDto;
import com.rookiefit.back.dto.response.notification.DeleteNotificationResponseDto;
import com.rookiefit.back.dto.response.notification.GetNotificationListResponseDto;
import com.rookiefit.back.dto.response.notification.GetNotificationResponseDto;
import com.rookiefit.back.entity.NotificationEntity;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.NotificationRepository;
import com.rookiefit.back.repository.UserRepository;
import com.rookiefit.back.service.NotificationService;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplement implements NotificationService{

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public ResponseEntity<? super InputNotificationResponseDto> createNotification(NotificationRequestDto dto) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        UserEntity userEntity = userRepository.findByUserId(currentUserId);
        if(userEntity == null) {
            return  ResponseEntity.badRequest().body("UserId not found");
        }
        NotificationEntity notificationEntity = new NotificationEntity(dto, currentUserId);
        notificationRepository.save(notificationEntity);

        return InputNotificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super InputNotificationResponseDto> updateNotification(NotificationRequestDto dto, Long notificationId) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        UserEntity userEntity = userRepository.findByUserId(currentUserId);
        if(userEntity == null) {
            return  ResponseEntity.badRequest().body("UserId not found");
        }
        Optional<NotificationEntity> optionalNotification = notificationRepository.findById(notificationId);
        if (optionalNotification.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Market item not found with ID: " + optionalNotification);
        }
        NotificationEntity notificationEntity = optionalNotification.get();

        // 2. Entity의 update 메서드 호출
        notificationEntity.update(dto);

        // 4. 엔티티 저장
        notificationRepository.save(notificationEntity);
        return InputNotificationResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetNotificationListResponseDto> getAllNotification() {
        List<NotificationEntity> notificationEntities = notificationRepository.findAll();
        return GetNotificationListResponseDto.success(notificationEntities);
    }

    @Override
    public ResponseEntity<? super GetNotificationResponseDto> getNotification(Long id) {
        NotificationEntity notificationEntitity = notificationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Notification not found with id: "+id));
        return GetNotificationResponseDto.success(notificationEntitity);
    }

    @Override
    public ResponseEntity<? super GetNotificationListResponseDto> searchNotification(String keyword, String field) {
        List<NotificationEntity> notificationEntities;
        // field 값에 따라 검색
        if ("title".equals(field)) {
            notificationEntities = notificationRepository.findByNotificationTitleContaining(keyword);
        } else if ("content".equals(field)) {
            notificationEntities = notificationRepository.findByNotificationContentContaining(keyword);
        } else if ("author".equals(field)) {
            notificationEntities = notificationRepository.findByNotificationAuthorContaining(keyword);
        } else {
            // field 값이 잘못된 경우 예외 처리
            return ResponseEntity.badRequest().build();
        }
        return GetNotificationListResponseDto.success(notificationEntities);
    }

    public ResponseEntity<? super DeleteNotificationResponseDto> deleteNotification(Long notificationId) {
        if (!notificationRepository.existsById(notificationId)) {
            return DeleteNotificationResponseDto.notificatonNotFound();
        }
        notificationRepository.deleteById(notificationId);
        return DeleteNotificationResponseDto.success();
    }
}
