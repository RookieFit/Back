package com.rookiefit.back.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.IdCheckRequestDto;
import com.rookiefit.back.dto.request.SignUpRequestDto;
import com.rookiefit.back.dto.response.auth.IdCheckResponseDto;
import com.rookiefit.back.dto.response.auth.SignUpResponseDto;
import com.rookiefit.back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck(
            @RequestBody @Valid IdCheckRequestDto dto) {
        ResponseEntity<? super IdCheckResponseDto> responseBody = authService.idCheck(dto);
        return responseBody;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }
}
