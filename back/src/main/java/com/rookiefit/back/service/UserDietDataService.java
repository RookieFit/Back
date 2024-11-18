package com.rookiefit.back.service;

import com.rookiefit.back.dto.request.UserDietData.InputUserDietListRequestDto;
import com.rookiefit.back.dto.response.UserDietData.InputUserDietListResponseDto;

import org.springframework.http.ResponseEntity;

public interface UserDietDataService {
    // 식단 데이터를 입력하는 메서드
    ResponseEntity<? super InputUserDietListResponseDto> inputUserDietData(InputUserDietListRequestDto dto);
}
