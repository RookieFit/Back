package com.rookiefit.back.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.UserDietData.InputFoodInfoRequestDto;
import com.rookiefit.back.dto.response.UserDietData.GetDietDataResponseDto;
import com.rookiefit.back.dto.response.UserDietData.InputFoodInfoResponseDto;
import com.rookiefit.back.entity.UserDiet.FoodInfoEntity;
import com.rookiefit.back.repository.UserDietData.FoodInfoRepository;
import com.rookiefit.back.service.FoodInfoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FoodInfoServiceImplement implements FoodInfoService {
    private final FoodInfoRepository foodInfoRepository;

    @Override
    public List<GetDietDataResponseDto> searchFoodByName(String keyword) {
        List<FoodInfoEntity> foods = foodInfoRepository.findByFoodNameContaining(keyword);
        return foods.stream()
                .map(GetDietDataResponseDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseEntity<? super InputFoodInfoResponseDto> insertFoodInfo(InputFoodInfoRequestDto dto) {
        // 음식 이름 중복 확인
        if (foodInfoRepository.existsByFoodName(dto.getFood_name())) {
            // 중복된 경우 실패 응답 반환
            return InputFoodInfoResponseDto.insertFail();
        }

        // 새로운 음식 데이터 삽입
        FoodInfoEntity foodInfoEntity = new FoodInfoEntity(dto);
        foodInfoRepository.save(foodInfoEntity);

        // 성공 응답 반환
        return InputFoodInfoResponseDto.success();
    }
}
