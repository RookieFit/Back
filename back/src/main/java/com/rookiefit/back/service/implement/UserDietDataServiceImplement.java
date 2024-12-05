package com.rookiefit.back.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    private final UserDietDataRepository userDietDataRepository;
    private final UserDietDetailDataRepository userDietDetailDataRepository;
    private final UserProfileRepository userProfileRepository;

    @Override
    @Transactional
    public ResponseEntity<? super InputUserDietListResponseDto> inputUserDietData(InputUserDietListRequestDto dto, String currentUserId) {

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

    //241205-09:55_(기능구현자 == {김경은})/Feat.김민준 : @RequestParam으로 교체
    @Override
    @Transactional
    public ResponseEntity<? super DeleteUserDietListResponseDto> deleteUserDietData(String diet_created_date, String currentUserId) {

        if (diet_created_date == null) {
            return DeleteUserDietListResponseDto.deleteFail();
        }

        // diet_created_date와 userId로 유효한 데이터 존재 여부 확인
        UserDietListDataEntity userDiet = userDietDataRepository
                .findByUserProfile_UserAuthEntity_UserIdAndDietCreatedDate(currentUserId, diet_created_date);

        if (userDiet == null) {
            // 해당 데이터를 찾을 수 없는 경우
            return DeleteUserDietListResponseDto.deleteFail();
        }
        // 해당 날짜에 대한 모든 데이터 삭제
        userDietDataRepository.deleteAllByUserProfile_UserAuthEntity_UserIdAndDietCreatedDate(
                currentUserId, diet_created_date);

        return DeleteUserDietListResponseDto.success();

    }

    //241205-09:43_(기능구현자 == {김경은})/Feat.김민준 : @RequestParam으로 교체
    @Override
    public ResponseEntity<List<GetDietDataDetailResponseDto>> getUserDietListData(String dietCreatedDate, String currentUserId) {

        // DietList 데이터 조회
        UserDietListDataEntity userDietListDataEntity = userDietDataRepository
                .findByUserProfile_UserAuthEntity_UserIdAndDietCreatedDate(currentUserId, dietCreatedDate);

        // 데이터가 없는 경우 응답
        if (userDietListDataEntity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // DietDetail 데이터 조회
        List<UserDietDetailDataEntity> dietDetailEntities = userDietDetailDataRepository
                .findAllByUserDietListData_DietCreatedDate(dietCreatedDate);

        // 데이터가 없는 경우 응답
        if (dietDetailEntities.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        // 총 칼로리 계산
        Double totalCalories = dietDetailEntities.stream()
                .mapToDouble(UserDietDetailDataEntity::getEnerc) // 칼로리 합산
                .sum();

        // 성공적으로 응답 반환
        return GetDietDataDetailResponseDto.success(dietDetailEntities, dietCreatedDate, totalCalories);
    }
}
