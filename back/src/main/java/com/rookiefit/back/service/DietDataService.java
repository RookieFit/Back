package com.rookiefit.back.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.response.UserDietData.GetDietDataResponseDto;
import com.rookiefit.back.entity.DietDataEntity;
import com.rookiefit.back.repository.DietDataRepository;

@Service
public class DietDataService {

    private final DietDataRepository dietDataRepository;

    public DietDataService(DietDataRepository dietDataRepository) {
        this.dietDataRepository = dietDataRepository;
    }

    // 음식 이름으로 검색하는 메서드
    public List<GetDietDataResponseDto> searchFoodByName(String keyword) {
        // keyword를 포함하는 음식 이름을 가진 엔티티들을 DB에서 조회
        List<DietDataEntity> foods = dietDataRepository.findByFoodNameContaining(keyword);

        // 엔티티 리스트를 DTO 리스트로 변환하여 반환
        return foods.stream()
                .map(GetDietDataResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
