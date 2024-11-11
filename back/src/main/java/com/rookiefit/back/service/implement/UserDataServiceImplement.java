package com.rookiefit.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.userData.GetUserProfileRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.userData.GetUserProfileResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserProfileResponseDto;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.service.UserDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDataServiceImplement implements UserDataService{

    private final UserProfileRepository userProfileRepository;
    private final UserProfileEntity userProfileEntity;

    @Override
    public ResponseEntity<? super InputUserProfileResponseDto> inputUserProfile(InputUserProfileRequestDto dto) {
        try {
            String userId = dto.getUserId();
            boolean isExsitedId = userProfileRepository.existsByUserId(userId);
            if(isExsitedId){
                userProfileRepository.deleteAllByUserId(userId);
            }
            UserProfileEntity userProfileEntity = new UserProfileEntity(dto);
            userProfileRepository.save(userProfileEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return InputUserProfileResponseDto.succes();
    }

    @Override
    public ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(GetUserProfileRequestDto dto) {
        try {
            String userId = dto.getUserId();
            boolean isExsitedUserId = userProfileRepository.existsByUserId(userId);
        } catch (Exception exception) {
            
        }
        return GetUserProfileResponseDto.success();
    }
    
}
