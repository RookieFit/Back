package com.rookiefit.back.service.implement;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.userData.InputUserBodyDataRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.dto.response.userData.GetUserBodyDataResponseDto;
import com.rookiefit.back.dto.response.userData.GetUserProfileResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserBodyDataResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserProfileResponseDto;
import com.rookiefit.back.entity.UserBodyDataEntity;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.repository.UserBodyDataRepository;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.repository.UserRepository;
import com.rookiefit.back.service.FirebaseService;
import com.rookiefit.back.service.UserDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDataServiceImplement implements UserDataService {

        private final UserProfileRepository userProfileRepository;
        private final UserRepository userRepository;
        private final UserBodyDataRepository userBodyDataRepository;
        private final FirebaseService firebaseService;

        @Override
        public ResponseEntity<? super InputUserProfileResponseDto> inputUserProfile(InputUserProfileRequestDto dto, String currentUserId) {
                boolean isExsitedId = userProfileRepository.existsByUserAuthEntity_UserId(currentUserId);
                if (isExsitedId) userProfileRepository.deleteAllByUserAuthEntity_UserId(currentUserId);
                String uploadedFileUrl = null;
                if (dto.getUserProfileImageFile() != null && !dto.getUserProfileImageFile().isEmpty()) {
                        try {
                        uploadedFileUrl = firebaseService.uploadFile(dto.getUserProfileImageFile());
                        } catch (IOException exception) {
                                exception.printStackTrace();
                        }
                }
                if(dto.getUserNickname() == null){ 
                        dto.setUserNickname("닉네임없는 헬린이");
                        System.out.println(dto.getUserNickname());
                }
                UserEntity userEntity = userRepository.findByUserId(currentUserId);
                UserProfileEntity userProfileEntity = new UserProfileEntity(dto,uploadedFileUrl,userEntity);
                userProfileRepository.save(userProfileEntity);
                return InputUserProfileResponseDto.succes();
        }

        @Override
        public ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(String currentUserId) {
                boolean isExsitedId = userProfileRepository.existsByUserAuthEntity_UserId(currentUserId);
                if (!isExsitedId) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); 

                UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
                return GetUserProfileResponseDto.success(userProfileEntity);
        }

        //241204-19:18_김민준 리팩토링
        @Override
        public ResponseEntity<? super InputUserBodyDataResponseDto> inputUserBodyData(InputUserBodyDataRequestDto dto, String currentUserId) {
                String currentDate = dto.getInbodydate();
                boolean isExsitedId = userBodyDataRepository.existsByInbodydate(currentDate);
                if (isExsitedId)userBodyDataRepository.deleteAllByInbodydate(currentDate);
                // UserProfileEntity 조회 (현재 userId를 기반으로 UserProfileEntity를 찾기)
                UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
                UserBodyDataEntity userBodyDataEntity = new UserBodyDataEntity(dto,userProfileEntity);

                userBodyDataRepository.save(userBodyDataEntity);
                return InputUserBodyDataResponseDto.success();
        }

        @Override
        public ResponseEntity<List<GetUserBodyDataResponseDto>> getUserBodyData(String currentUserId) {
                boolean isExsitedId = userBodyDataRepository.existsByUserProfile_UserAuthEntity_UserId(currentUserId);
                if (!isExsitedId) {
                        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
                }
                List<UserBodyDataEntity> responseDtos = userBodyDataRepository.findUserBodyDataByUserId(currentUserId);
                return GetUserBodyDataResponseDto.success(responseDtos);
        }
}
