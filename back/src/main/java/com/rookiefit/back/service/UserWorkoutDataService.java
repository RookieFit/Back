package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDataRequestDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutDataResponseDto;

import jakarta.validation.Valid;

public interface UserWorkoutDataService {
    ResponseEntity<? super InputUserWorkoutDataResponseDto> inputUserWorkoutData(InputUserWorkoutDataRequestDto dto);
    
}
