package com.rookiefit.back.service;

import com.rookiefit.back.dto.request.UserDietData.DeleteUserDietListRequestDto;
import com.rookiefit.back.dto.request.UserDietData.GetDietDataDetailRequestDto;
import com.rookiefit.back.dto.request.UserDietData.InputUserDietListRequestDto;
import com.rookiefit.back.dto.response.UserDietData.DeleteUserDietListResponseDto;
import com.rookiefit.back.dto.response.UserDietData.GetDietDataDetailResponseDto;
import com.rookiefit.back.dto.response.UserDietData.InputUserDietListResponseDto;

import java.util.List;

import org.springframework.http.ResponseEntity;

public interface UserDietDataService {
    // 식단 데이터를 입력하는 메서드
    ResponseEntity<? super InputUserDietListResponseDto> inputUserDietData(InputUserDietListRequestDto dto);

    // 식단 데이터를 삭제하는 메서드
    ResponseEntity<? super DeleteUserDietListResponseDto> deleteUserDietData(DeleteUserDietListRequestDto dto);

    ResponseEntity<List<GetDietDataDetailResponseDto>> getUserDietListData(GetDietDataDetailRequestDto dto);
}
