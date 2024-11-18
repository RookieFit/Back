package com.rookiefit.back.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.dto.request.UserDietData.InputUserDietDetailRequestDto;
import com.rookiefit.back.dto.request.UserDietData.InputUserDietListRequestDto;
import com.rookiefit.back.dto.response.UserDietData.InputUserDietListResponseDto;
import com.rookiefit.back.entity.UserDietDetailDataEntity;
import com.rookiefit.back.entity.UserDietListDataEntity;
import com.rookiefit.back.repository.UserDietDataRepository;
import com.rookiefit.back.repository.UserDietDetailDataRepository;
import com.rookiefit.back.service.UserDietDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDietDataServiceImplement implements UserDietDataService {

    private final JwtProvider jwtProvider;
    private final UserDietDataRepository userDietDataRepository;
    private final UserDietDetailDataRepository userDietDetailDataRepository;

    @Override
    public ResponseEntity<? super InputUserDietListResponseDto> inputUserDietData(
            InputUserDietListRequestDto dto) {
        // 토큰에서 userId 추출
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        dto.setToken(currentUserId); // userId 저장

        // 기존 Diet 데이터 조회 (해당 날짜와 사용자에 대해)
        UserDietListDataEntity userDietListDataEntity = userDietDataRepository
                .findByUserProfileUserIdAndDietCreateDate(currentUserId, dto.getDiet_create_date());

        if (userDietListDataEntity != null) {
            // 기존 데이터가 있으면 해당 Diet에 음식을 추가
            userDietListDataEntity.setTotalCalories(dto.getTotal_calories());
            userDietListDataEntity.setDietCreatedDate(dto.getDiet_create_date());

            // 기존 DietDetail 삭제 (새로운 식단 데이터로 덮어쓰기)
            userDietDetailDataRepository.deleteByUserDietDataUserProfileUserIdAndUserDietDataDietCreateDate(
                    currentUserId, dto.getDiet_create_date());
        } else {
            // 새로운 엔티티 생성
            userDietListDataEntity = new UserDietListDataEntity(dto, null);
        }

        // DietList 데이터 저장
        userDietDataRepository.save(userDietListDataEntity);

        // 새로운 DietDetails 저장
        List<InputUserDietDetailRequestDto> dietDetails = dto.getDietDetails();
        for (InputUserDietDetailRequestDto dietDetailDto : dietDetails) {
            UserDietDetailDataEntity userDietDetailDataEntity = new UserDietDetailDataEntity(dietDetailDto);
            userDietDetailDataEntity.setUserDietList(userDietListDataEntity); // 외래 키 설정
            userDietDetailDataRepository.save(userDietDetailDataEntity);
        }

        // 응답 반환
        return ResponseEntity.ok(InputUserDietListResponseDto.success());
    }
}
