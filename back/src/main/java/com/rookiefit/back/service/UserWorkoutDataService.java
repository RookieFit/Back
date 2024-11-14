package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userWorkoutData.DeleteUserWorkoutListRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutDetailRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutListRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.dto.response.userWorkoutData.DeleteUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutDetailResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;


public interface UserWorkoutDataService {
    
    ResponseEntity<? super InputUserWorkoutListResponseDto> inputUserWorkoutData(InputUserWorkoutListRequestDto dto);

    ResponseEntity<? super GetUserWorkoutListResponseDto> getUserWorkoutData(GetUserWorkoutListRequestDto dto);

    ResponseEntity<? super GetUserWorkoutDetailResponseDto> getUserWorkoutDetail(GetUserWorkoutDetailRequestDto dto);
    
    ResponseEntity<? super DeleteUserWorkoutListResponseDto> deleteUserWorkoutList(DeleteUserWorkoutListRequestDto dto);
}
