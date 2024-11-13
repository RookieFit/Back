package com.rookiefit.back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.response.userWorkoutData.DeleteUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutDetailResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;
import com.rookiefit.back.dto.request.userWorkoutData.DeleteUserWorkoutListRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutDetailRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutListRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.service.UserWorkoutDataService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserWorkoutDataController {
    private final UserWorkoutDataService userWorkoutDataService;

    @PostMapping("/input-userworkoutlistdata")
    public ResponseEntity<? super InputUserWorkoutListResponseDto> inputUserWorkoutData(
        @RequestBody @Valid InputUserWorkoutListRequestDto dto) {
            ResponseEntity<? super InputUserWorkoutListResponseDto> responseBody = userWorkoutDataService.inputUserWorkoutData(dto);
            return responseBody;
    }

    @GetMapping("/userworkoutlistdata")
    public ResponseEntity<? super GetUserWorkoutListResponseDto> getUserWorkoutData(
        @RequestBody @Valid GetUserWorkoutListRequestDto dto){
            ResponseEntity<? super GetUserWorkoutListResponseDto> responseBody = userWorkoutDataService.getUserWorkoutData(dto);
            return responseBody;
    }

    @GetMapping("/userworkoutdetaildata")
    public ResponseEntity<? super GetUserWorkoutDetailResponseDto> getUserWorkoutDetail(
        @RequestBody @Valid GetUserWorkoutDetailRequestDto dto) {
            ResponseEntity<? super GetUserWorkoutDetailResponseDto> responseBody = userWorkoutDataService.getUserWorkoutDetail(dto);
            return responseBody;
    }

    @DeleteMapping("/delete-userworkoutlistdata")
    public ResponseEntity<? super DeleteUserWorkoutListResponseDto> deleteUserWorkoutList(
        @RequestBody @Valid DeleteUserWorkoutListRequestDto dto) {
            ResponseEntity<? super DeleteUserWorkoutListResponseDto> responseBody = userWorkoutDataService.deleteUserWorkoutList(dto);
            return responseBody;
    }
}
