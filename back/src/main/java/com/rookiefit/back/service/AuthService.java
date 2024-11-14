package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

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
import com.rookiefit.back.dto.response.auth.CheckCertificationResponseDto;
import com.rookiefit.back.dto.response.auth.CheckFindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.FindUserIdResponseDto;
import com.rookiefit.back.dto.response.auth.CheckFindUserPasswordResponseDto;
import com.rookiefit.back.dto.response.auth.FindUserPasswordResponseDto;
import com.rookiefit.back.dto.response.auth.IdCheckResponseDto;
import com.rookiefit.back.dto.response.auth.SignUpResponseDto;
import com.rookiefit.back.dto.response.auth.SmsCertificationResponseDto;
import com.rookiefit.back.dto.response.auth.UserDeleteResponseDto;
import com.rookiefit.back.dto.response.auth.SignInResponseDto;

public interface AuthService {

    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    ResponseEntity<? super SmsCertificationResponseDto> smsCertification(SmsCertificationRequestDto dto);

    ResponseEntity<? super SignUpResponseDto> signUp(SignUpRequestDto dto);

    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);

    ResponseEntity<? super FindUserIdResponseDto> findUserId(FindUserIdRequestDto dto);

    ResponseEntity<? super CheckFindUserIdResponseDto> checkFindUserId(CheckFindUserIdRequestDto dto);

    ResponseEntity<? super FindUserPasswordResponseDto> findUserPassword(FindUserPasswordRequestDto dto);

    ResponseEntity<? super CheckFindUserPasswordResponseDto> checkFindUserPasswordResponseDto(
            CheckFindUserPasswordRequestDto dto);

    ResponseEntity<? super UserDeleteResponseDto> userDelete(UserDeleteRequestDto dto);
}
