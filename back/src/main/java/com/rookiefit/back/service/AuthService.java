package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.IdCheckRequestDto;
import com.rookiefit.back.dto.request.SignInRequestDto;
import com.rookiefit.back.dto.request.SignUpRequestDto;
import com.rookiefit.back.dto.response.auth.IdCheckResponseDto;
import com.rookiefit.back.dto.response.auth.SignUpResponseDto;
import com.rookiefit.back.dto.response.auth.SignInResponseDto;

public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
