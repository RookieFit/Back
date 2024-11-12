package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutDataRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutDataResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;


public interface UserWorkoutDataService {
    
    ResponseEntity<? super InputUserWorkoutListResponseDto> inputUserWorkoutData(InputUserWorkoutListRequestDto dto);

    ResponseEntity<? super GetUserWorkoutDataResponseDto> getUserWorkoutData(GetUserWorkoutDataRequestDto dto);
    
}
