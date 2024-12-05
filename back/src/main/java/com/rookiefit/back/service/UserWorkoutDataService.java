package com.rookiefit.back.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.dto.response.userWorkoutData.DeleteUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutDetailResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;


public interface UserWorkoutDataService {
    
    ResponseEntity<? super InputUserWorkoutListResponseDto> inputUserWorkoutData(InputUserWorkoutListRequestDto dto, String currentUserId);

    ResponseEntity<List<GetUserWorkoutListResponseDto>> getUserWorkoutData(String currentUserId);

    ResponseEntity<List<GetUserWorkoutDetailResponseDto>> getUserWorkoutDetail(String currentUserId, String workoutDetailCreatedDate);
    
    ResponseEntity<? super DeleteUserWorkoutListResponseDto> deleteUserWorkoutList(String currentUserId, String workoutCreatedDate);
}
