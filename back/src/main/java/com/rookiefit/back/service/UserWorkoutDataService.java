package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutDataRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDataRequestDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutDataResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutDataResponseDto;


public interface UserWorkoutDataService {
    
    ResponseEntity<? super InputUserWorkoutDataResponseDto> inputUserWorkoutData(InputUserWorkoutDataRequestDto dto);

    ResponseEntity<? super GetUserWorkoutDataResponseDto> getUserWorkoutData(GetUserWorkoutDataRequestDto dto);
    
}
