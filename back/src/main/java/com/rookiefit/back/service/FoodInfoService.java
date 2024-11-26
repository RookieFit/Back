package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.UserDietData.InputFoodInfoRequestDto;
import com.rookiefit.back.dto.response.UserDietData.GetDietDataResponseDto;
import com.rookiefit.back.dto.response.UserDietData.InputFoodInfoResponseDto;

import java.util.List;

public interface FoodInfoService {

    /**
     * 음식 이름으로 검색하는 메서드
     *
     * @param keyword 음식 이름에 포함된 검색어
     * @return 검색 결과 리스트 (DTO 형식)
     */
    List<GetDietDataResponseDto> searchFoodByName(String keyword);

    /**
     * 새로운 음식 데이터를 삽입하는 메서드
     *
     * @param dto 음식 데이터 요청 DTO
     * @return 삽입 결과 응답
     */
    ResponseEntity<? super InputFoodInfoResponseDto> insertFoodInfo(InputFoodInfoRequestDto dto);
}
