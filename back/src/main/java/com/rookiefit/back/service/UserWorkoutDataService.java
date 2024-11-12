package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutListRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDetailRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutDetailResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;


public interface UserWorkoutDataService {
    
    ResponseEntity<? super InputUserWorkoutListResponseDto> inputUserWorkoutData(InputUserWorkoutListRequestDto dto);

    ResponseEntity<? super GetUserWorkoutListResponseDto> getUserWorkoutData(GetUserWorkoutListRequestDto dto);

    ResponseEntity<? super InputUserWorkoutDetailResponseDto> inputUserWorkoutDetail(InputUserWorkoutDetailRequestDto dto);
    
}
