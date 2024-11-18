package com.rookiefit.back.controllers;

import com.rookiefit.back.dto.request.UserDietData.InputUserDietListRequestDto;
import com.rookiefit.back.dto.response.UserDietData.InputUserDietListResponseDto;
import com.rookiefit.back.service.UserDietDataService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserDietDataController {

    private final UserDietDataService userDietDataService;

    @PostMapping("/input-userdietlistdata")
    public ResponseEntity<? super InputUserDietListResponseDto> inputUserDietData(
            @RequestBody @Valid InputUserDietListRequestDto dto) {
        // 서비스로 전달하여 응답을 받음
        return userDietDataService.inputUserDietData(dto);
    }
}