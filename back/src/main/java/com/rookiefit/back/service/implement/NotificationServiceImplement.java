package com.rookiefit.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.admin.NotificationRequestDto;
import com.rookiefit.back.dto.response.admin.CreateNotificationResponseDto;
import com.rookiefit.back.entity.NotificationEntity;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.NotificationRepository;
import com.rookiefit.back.repository.UserRepository;
import com.rookiefit.back.service.NotificationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NotificationServiceImplement implements NotificationService{

    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @Override
    public ResponseEntity<? super CreateNotificationResponseDto> createNotification(NotificationRequestDto dto) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        UserEntity userEntity = userRepository.findByUserId(currentUserId);
        if(userEntity == null) {
            return  ResponseEntity.badRequest().body("UserId not found");
        }
        NotificationEntity notificationEntity = new NotificationEntity(dto, currentUserId);
        notificationRepository.save(notificationEntity);

        return CreateNotificationResponseDto.success();
    }
}
