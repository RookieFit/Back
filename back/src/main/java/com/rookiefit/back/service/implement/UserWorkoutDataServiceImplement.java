package com.rookiefit.back.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rookiefit.back.dto.request.userWorkoutData.DeleteUserWorkoutListRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutDetailRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutListRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDetailRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.dto.response.userWorkoutData.DeleteUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutDetailResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;
import com.rookiefit.back.entity.UserWorkoutDetailDataEntity;
import com.rookiefit.back.entity.UserWorkoutListDataEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserWorkoutDetailDataRepository;
import com.rookiefit.back.repository.UserWorkoutListDataRepository;
import com.rookiefit.back.service.UserWorkoutDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserWorkoutDataServiceImplement implements UserWorkoutDataService{

    private final JwtProvider jwtProvider;
    private final UserWorkoutListDataRepository userWorkoutListDataRepository;
    private final UserWorkoutDetailDataRepository userWorkoutDetailDataRepository;

    @Transactional
    @Override
    public ResponseEntity<? super InputUserWorkoutListResponseDto> inputUserWorkoutData(InputUserWorkoutListRequestDto dto) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken()); // 토큰에서 userId 추출
        dto.setToken(currentUserId); // userId 저장
    
        // 기존 WorkoutList 데이터 조회
        UserWorkoutListDataEntity userWorkoutListDataEntity = userWorkoutListDataRepository.findByUserIdAndWorkoutCreatedDate(currentUserId, dto.getWorkoutCreatedData());
        
        if (userWorkoutListDataEntity != null) {
            // 이미 존재하는 경우 데이터 업데이트
            userWorkoutListDataEntity.setComment(dto.getComment());
            userWorkoutListDataEntity.setWorkoutTitle(dto.getWorkout_title());
            // 기존 WorkoutDetails 삭제 후 새로운 WorkoutDetails 저장
            userWorkoutDetailDataRepository.deleteByUserWorkoutList(userWorkoutListDataEntity);
            
        } else {
            // 새로운 엔티티 생성
            userWorkoutListDataEntity = new UserWorkoutListDataEntity(dto);
            userWorkoutListDataEntity.setUserId(currentUserId);
        }
        
        // WorkoutList 데이터 저장
        userWorkoutListDataRepository.save(userWorkoutListDataEntity);
    
        // 새로운 WorkoutDetails 저장
        List<InputUserWorkoutDetailRequestDto> workoutDetails = dto.getWorkoutDetails();
        for (InputUserWorkoutDetailRequestDto workoutDetailDto : workoutDetails) {
            UserWorkoutDetailDataEntity userWorkoutDetailDataEntity = new UserWorkoutDetailDataEntity(workoutDetailDto);
            userWorkoutDetailDataEntity.setUserWorkoutList(userWorkoutListDataEntity); // 외래 키 설정
            userWorkoutDetailDataRepository.save(userWorkoutDetailDataEntity);
        }
        
        return InputUserWorkoutListResponseDto.success();
    }

    @Override
    public ResponseEntity<List<GetUserWorkoutListResponseDto>> getUserWorkoutData(GetUserWorkoutListRequestDto dto) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        boolean isExsitedId = userWorkoutListDataRepository.existsByUserId(currentUserId);
        if(!isExsitedId){System.out.println("아이디가존재하지않음");};

        List<UserWorkoutListDataEntity> userWorkoutListDataEntities = userWorkoutListDataRepository.findWorkoutListByUserId(currentUserId);
        return GetUserWorkoutListResponseDto.success(userWorkoutListDataEntities);
    }
    @Override
    public ResponseEntity<List<GetUserWorkoutDetailResponseDto>> getUserWorkoutDetail(GetUserWorkoutDetailRequestDto dto) {
        String currentDate = dto.getWorkoutDetailCreatedDate();
        List<UserWorkoutDetailDataEntity> userWorkoutDetailDataEntity = userWorkoutDetailDataRepository.findByWorkoutDetailCreatedDate(currentDate);
        return GetUserWorkoutDetailResponseDto.success(userWorkoutDetailDataEntity);  // Pass the entity list to the DTO's success method
    }

    @Override
    public ResponseEntity<? super DeleteUserWorkoutListResponseDto> deleteUserWorkoutList(DeleteUserWorkoutListRequestDto dto) {
        // 사용자 ID 추출
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());

        // 삭제할 운동 목록을 DB에서 찾기
        UserWorkoutListDataEntity userWorkoutListDataEntity = userWorkoutListDataRepository.findByUserIdAndWorkoutCreatedDate(currentUserId, dto.getWorkoutCreatedData());
        // 운동 목록이 존재하는지 확인
        if (userWorkoutListDataEntity == null) {
        // 운동 목록이 없으면 에러 응답 반환
        return DeleteUserWorkoutListResponseDto.failure("운동 목록을 찾을 수 없습니다.");
        }
        // 운동 목록 삭제
        userWorkoutListDataRepository.delete(userWorkoutListDataEntity);
        // 성공 응답 반환
        return DeleteUserWorkoutListResponseDto.success();
    }
} 