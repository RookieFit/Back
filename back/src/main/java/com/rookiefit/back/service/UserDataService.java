package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userData.GetUserProfileRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.dto.response.userData.GetUserProfileResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserProfileResponseDto;

public interface UserDataService {
    
    ResponseEntity<? super InputUserProfileResponseDto> inputUserProfile(InputUserProfileRequestDto dto);

    ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(GetUserProfileRequestDto dto);
}
