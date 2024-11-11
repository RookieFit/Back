package com.rookiefit.back.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.dto.request.userData.GetUserProfileRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserBodyDataRequestDto;
import com.rookiefit.back.dto.response.userData.InputUserProfileResponseDto;
import com.rookiefit.back.dto.response.userData.GetUserProfileResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserBodyDataResponseDto;
import com.rookiefit.back.service.UserDataService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserDataController {
    private final UserDataService userDataService;

    @PostMapping("/input-userprofile")
    public ResponseEntity<? super InputUserProfileResponseDto>inputUserProfile(
        @RequestBody @Valid InputUserProfileRequestDto dto) {
            ResponseEntity<? super InputUserProfileResponseDto> responseBody = userDataService.inputUserProfile(dto);
            return responseBody;
    }

    @GetMapping("/userprofile")
    public ResponseEntity<? super GetUserProfileResponseDto>userProfile(
        @RequestBody @Valid GetUserProfileRequestDto dto) {
            ResponseEntity<? super GetUserProfileResponseDto> responseBody = userDataService.getUserProfile(dto);
            return responseBody;
    }

    @PostMapping("/input-userbodydata")
    public ResponseEntity<? super InputUserBodyDataResponseDto>inputUserBodyData(
        @RequestBody @Valid InputUserBodyDataRequestDto dto) {
            ResponseEntity<? super InputUserBodyDataResponseDto> responseBody = userDataService.inputUserBodyData(dto);
            return responseBody;
        }
}
