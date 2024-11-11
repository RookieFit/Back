package com.rookiefit.back.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.dto.response.userData.InputUserProfileResponseDto;
import com.rookiefit.back.service.UserDataService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
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
            System.out.println("controller");
            return responseBody;
    }
}
