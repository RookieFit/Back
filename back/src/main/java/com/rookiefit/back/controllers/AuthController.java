package com.rookiefit.back.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.CheckCertificationRequestDto;
import com.rookiefit.back.dto.request.CheckFindUserIdRequestDto;
import com.rookiefit.back.dto.request.FindUserIdRequestDto;
import com.rookiefit.back.dto.request.CheckFindUserPasswordRequestDto;
import com.rookiefit.back.dto.request.FindUserPasswordRequestDto;
import com.rookiefit.back.dto.request.IdCheckRequestDto;
import com.rookiefit.back.dto.request.SignInRequestDto;
import com.rookiefit.back.dto.request.SignUpRequestDto;
import com.rookiefit.back.dto.request.SmsCertificationRequestDto;
import com.rookiefit.back.dto.response.auth.IdCheckResponseDto;
import com.rookiefit.back.dto.response.auth.SignInResponseDto;
import com.rookiefit.back.dto.response.auth.SignUpResponseDto;
import com.rookiefit.back.dto.response.auth.SmsCertificationResponseDto;
import com.rookiefit.back.dto.response.auth.CheckCertificationResponseDto;
import com.rookiefit.back.dto.response.auth.CheckFindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.FindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.CheckFindUserPasswordResponseDto;
import com.rookiefit.back.dto.response.auth.FindUserPasswordResponseDto;
import com.rookiefit.back.dto.response.auth.SignInResponseDto;
import com.rookiefit.back.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck(
            @RequestBody @Valid IdCheckRequestDto dto) {
        ResponseEntity<? super IdCheckResponseDto> responseBody = authService.idCheck(dto);
        return responseBody;
    }

    @PostMapping("/sms-certification")
    public ResponseEntity<? super SmsCertificationResponseDto> smsCertification(
            @RequestBody @Valid SmsCertificationRequestDto dto) {
        ResponseEntity<? super SmsCertificationResponseDto> responseBody = authService.smsCertification(dto);
        return responseBody;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Valid CheckCertificationRequestDto dto) {
        ResponseEntity<? super CheckCertificationResponseDto> responseBody = authService.checkCertification(dto);
        return responseBody;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<? super SignUpResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }


    @PostMapping("/find-id")
    public ResponseEntity<? super FindUserIdResponseDto> findUserId(
            @RequestBody @Valid FindUserIdRequestDto requestBody) {
        ResponseEntity<? super FindUserIdResponseDto> response = authService.findUserId(requestBody);
        return response;
    }

    @PostMapping("/check-find-id")
    public ResponseEntity<? super CheckFindUserIdResponseDto> checkFindUserId(
            @RequestBody @Valid CheckFindUserIdRequestDto dto) {
        ResponseEntity<? super CheckFindUserIdResponseDto> responseBody = authService.checkFindUserId(dto);
        return responseBody;
    }
    @PostMapping("/find-password")
    public ResponseEntity<? super FindUserPasswordResponseDto> findUserPassword(
        @RequestBody @Valid FindUserPasswordRequestDto requestBody){
            ResponseEntity<? super FindUserPasswordResponseDto> response = authService.findUserPassword(requestBody);
            return response;
        }
    
    @PostMapping("/check-find-password")
    public ResponseEntity<? super CheckFindUserPasswordResponseDto> checkFindUserPasswordResponseDto(
        @RequestBody @Valid CheckFindUserPasswordRequestDto requestBody){
            ResponseEntity<? super CheckFindUserPasswordResponseDto> response = authService.checkFindUserPasswordResponseDto(requestBody);
            return response;
        }
}
