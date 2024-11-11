package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userData.UserProfileInputRequestDto;
import com.rookiefit.back.dto.response.userData.UserProfileInputResponseDto;

public interface UserDataService {

    ResponseEntity<? super UserProfileInputResponseDto> inputUserProfile(UserProfileInputRequestDto dto);
}
