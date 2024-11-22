package com.rookiefit.back.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.dto.request.UserDietData.DeleteUserDietListRequestDto;
import com.rookiefit.back.dto.request.UserDietData.GetDietDataDetailRequestDto;
import com.rookiefit.back.dto.request.UserDietData.InputUserDietDetailRequestDto;
import com.rookiefit.back.dto.request.UserDietData.InputUserDietListRequestDto;
import com.rookiefit.back.dto.response.UserDietData.DeleteUserDietListResponseDto;
import com.rookiefit.back.dto.response.UserDietData.GetDietDataDetailResponseDto;
import com.rookiefit.back.dto.response.UserDietData.InputUserDietListResponseDto;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.entity.UserDiet.UserDietDetailDataEntity;
import com.rookiefit.back.entity.UserDiet.UserDietListDataEntity;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.repository.UserDietData.UserDietDataRepository;
import com.rookiefit.back.repository.UserDietData.UserDietDetailDataRepository;
import com.rookiefit.back.service.UserDietDataService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDietDataServiceImplement implements UserDietDataService {

    private final JwtProvider jwtProvider;
    private final UserDietDataRepository userDietDataRepository;
    private final UserDietDetailDataRepository userDietDetailDataRepository;
    private final UserProfileRepository userProfileRepository;

    @Override
    @Transactional
    public ResponseEntity<? super InputUserDietListResponseDto> inputUserDietData(InputUserDietListRequestDto dto) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        dto.setToken(currentUserId);

        // 기존 Diet 데이터 조회
        UserDietListDataEntity userDietListDataEntity = userDietDataRepository
                .findByUserProfile_UserAuthEntity_UserIdAndDietCreatedDate(currentUserId, dto.getDiet_created_date());

        if (userDietListDataEntity == null) {
            // 새로운 엔티티 생성
            UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
            userDietListDataEntity = new UserDietListDataEntity(dto, userProfileEntity);

            // DietList 데이터 저장
            userDietDataRepository.save(userDietListDataEntity);

            // 새로운 DietDetails 저장
            for (InputUserDietDetailRequestDto dietDetailDto : dto.getDietDetails()) {
                UserDietDetailDataEntity userDietDetailDataEntity = new UserDietDetailDataEntity(dietDetailDto,
                        userDietListDataEntity);
                userDietDetailDataRepository.save(userDietDetailDataEntity);
            }
        } else {
            // 기존 데이터 삭제 (기존 DietDetail 삭제)
            userDietDetailDataRepository.deleteAllByUserDietListData(userDietListDataEntity);

            // 새로운 DietDetails 저장
            for (InputUserDietDetailRequestDto dietDetailDto : dto.getDietDetails()) {
                UserDietDetailDataEntity userDietDetailDataEntity = new UserDietDetailDataEntity(dietDetailDto,
                        userDietListDataEntity);
                userDietDetailDataRepository.save(userDietDetailDataEntity);
            }
        }

        // 응답 반환
        return InputUserDietListResponseDto.success();
    }

    @Override
    @Transactional
    public ResponseEntity<? super DeleteUserDietListResponseDto> deleteUserDietData(DeleteUserDietListRequestDto dto) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());

        // 요청에서 받은 diet_created_date 확인
        String dietCreatedDate = dto.getDiet_created_date();
        if (dietCreatedDate == null) {
            return DeleteUserDietListResponseDto.deleteFail();
        }

        // diet_created_date와 userId로 유효한 데이터 존재 여부 확인
        UserDietListDataEntity userDiet = userDietDataRepository
                .findByUserProfile_UserAuthEntity_UserIdAndDietCreatedDate(currentUserId, dietCreatedDate);

        if (userDiet == null) {
            // 해당 데이터를 찾을 수 없는 경우
            return DeleteUserDietListResponseDto.deleteFail();
        }
        // 해당 날짜에 대한 모든 데이터 삭제
        userDietDataRepository.deleteAllByUserProfile_UserAuthEntity_UserIdAndDietCreatedDate(
                currentUserId, dietCreatedDate);

        return DeleteUserDietListResponseDto.success();

    }

    @Override
    public ResponseEntity<List<GetDietDataDetailResponseDto>> getUserDietListData(GetDietDataDetailRequestDto dto) {
        // 토큰에서 사용자 ID 추출
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        String dietCreatedDate = dto.getDiet_created_date();

        // DietList 데이터 조회
        UserDietListDataEntity userDietListDataEntity = userDietDataRepository
                .findByUserProfile_UserAuthEntity_UserIdAndDietCreatedDate(currentUserId, dietCreatedDate);

        // 데이터가 없는 경우 응답
        if (userDietListDataEntity == null) {
            System.out.println("데이터가 존재하지 않습니다.");
        }

        // DietDetail 데이터 조회
        List<UserDietDetailDataEntity> dietDetailEntities = userDietDetailDataRepository
                .findAllByUserDietListData_DietCreatedDate(dietCreatedDate);

        // 데이터가 없는 경우 응답
        if (dietDetailEntities.isEmpty()) {
            System.out.println("DietDetail 데이터가 존재하지 않습니다.");
        }

        // 총 칼로리 계산
        double totalCalories = dietDetailEntities.stream()
                .mapToDouble(UserDietDetailDataEntity::getEnerc) // 칼로리 합산
                .sum();

        // 성공적으로 응답 반환
        return GetDietDataDetailResponseDto.success(dietDetailEntities, dietCreatedDate, totalCalories);
    }
}
