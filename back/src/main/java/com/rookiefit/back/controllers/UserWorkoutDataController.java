package com.rookiefit.back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutDataResponseDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDataRequestDto;
import com.rookiefit.back.service.UserWorkoutDataService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserWorkoutDataController {
    private final UserWorkoutDataService userWorkoutDataService;

    @PostMapping("/input-userworkoutdata")
    public ResponseEntity<? super InputUserWorkoutDataResponseDto> inputUserWorkoutData(
        @RequestBody @Valid InputUserWorkoutDataRequestDto dto) {
            ResponseEntity<? super InputUserWorkoutDataResponseDto> responseBody = userWorkoutDataService.inputUserWorkoutData(dto);
            return responseBody;
    }
}
