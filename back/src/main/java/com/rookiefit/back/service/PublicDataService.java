package com.rookiefit.back.service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import com.rookiefit.back.dto.response.PublicDataResponseDto;
import com.rookiefit.back.entity.PublicDataEntity;
import com.rookiefit.back.repository.PublicDataRepository;

@Service
public class PublicDataService {

    private final PublicDataRepository publicDataRepository;

    public PublicDataService(PublicDataRepository publicDataRepository) {
        this.publicDataRepository = publicDataRepository;
    }

    // 음식 이름으로 검색하는 메서드
    public List<PublicDataResponseDto> searchFoodByName(String keyword) {
        // keyword를 포함하는 음식 이름을 가진 엔티티들을 DB에서 조회
        List<PublicDataEntity> foods = publicDataRepository.findByFoodNameContaining(keyword);

        // 엔티티 리스트를 DTO 리스트로 변환하여 반환
        return foods.stream()
                .map(PublicDataResponseDto::fromEntity)
                .collect(Collectors.toList());
    }
}
