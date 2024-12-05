package com.rookiefit.back.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.auth.CheckCertificationRequestDto;
import com.rookiefit.back.dto.request.auth.CheckFindUserIdRequestDto;
import com.rookiefit.back.dto.request.auth.CheckFindUserPasswordRequestDto;
import com.rookiefit.back.dto.request.auth.FindUserIdRequestDto;
import com.rookiefit.back.dto.request.auth.FindUserPasswordRequestDto;
import com.rookiefit.back.dto.request.auth.IdCheckRequestDto;
import com.rookiefit.back.dto.request.auth.SignInRequestDto;
import com.rookiefit.back.dto.request.auth.SignUpRequestDto;
import com.rookiefit.back.dto.request.auth.SmsCertificationRequestDto;
import com.rookiefit.back.dto.request.auth.UserDeleteRequestDto;
import com.rookiefit.back.dto.request.trainer.InputTrainerRequestDto;
import com.rookiefit.back.dto.response.auth.IdCheckResponseDto;
import com.rookiefit.back.dto.response.auth.SignInResponseDto;
import com.rookiefit.back.dto.response.auth.SignUpResponseDto;
import com.rookiefit.back.dto.response.auth.SmsCertificationResponseDto;
import com.rookiefit.back.dto.response.auth.UserDeleteResponseDto;
import com.rookiefit.back.dto.response.trainer.InputTrainerResponseDto;
import com.rookiefit.back.dto.response.auth.CheckCertificationResponseDto;
import com.rookiefit.back.dto.response.auth.CheckFindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.FindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.CheckFindUserPasswordResponseDto;
import com.rookiefit.back.dto.response.auth.FindUserPasswordResponseDto;
import com.rookiefit.back.service.AuthService;
import com.rookiefit.back.service.TrainerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final TrainerService trainerService;

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
            @RequestBody @Valid SignUpRequestDto dto) {
        ResponseEntity<? super SignUpResponseDto> response = authService.signUp(dto);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto dto) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(dto);
        return response;
    }

    @PostMapping("/find-id")
    public ResponseEntity<? super FindUserIdResponseDto> findUserId(
            @RequestBody @Valid FindUserIdRequestDto dto) {
        ResponseEntity<? super FindUserIdResponseDto> response = authService.findUserId(dto);
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
            @RequestBody @Valid FindUserPasswordRequestDto dto) {
        ResponseEntity<? super FindUserPasswordResponseDto> response = authService.findUserPassword(dto);
        return response;
    }

    @PostMapping("/check-find-password")
    public ResponseEntity<? super CheckFindUserPasswordResponseDto> checkFindUserPassword(
            @RequestBody @Valid CheckFindUserPasswordRequestDto dto) {
        ResponseEntity<? super CheckFindUserPasswordResponseDto> response = authService
                .checkFindUserPasswordResponseDto(dto);
        return response;
    }

    @PostMapping("/user-delete")
    public ResponseEntity<? super UserDeleteResponseDto> userDelete(
            @RequestBody @Valid UserDeleteRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String) authentication.getPrincipal();
        ResponseEntity<? super UserDeleteResponseDto> response = authService.userDelete(dto, currentUserId);
        return response;
    }

    // 관리자인지 인증
    @GetMapping("/roles")
    public ResponseEntity<List<String>> getUserRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return ResponseEntity.ok(roles);
    }

    // 트레이너 등록 요청
    @PostMapping("/trainer-register")
    public ResponseEntity<? super InputTrainerResponseDto> createTrainer(
            @RequestBody @Valid InputTrainerRequestDto dto) {
        ResponseEntity<? super InputTrainerResponseDto> response = trainerService.createTrainer(dto);
        return response;
    }
}
