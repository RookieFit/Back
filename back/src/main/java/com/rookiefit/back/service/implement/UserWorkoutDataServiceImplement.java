package com.rookiefit.back.service.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.dto.response.userWorkoutData.DeleteUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutDetailResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.entity.UserWorkout.UserWorkoutDetailDataEntity;
import com.rookiefit.back.entity.UserWorkout.UserWorkoutImagesEntity;
import com.rookiefit.back.entity.UserWorkout.UserWorkoutListDataEntity;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.repository.UserWorkout.UserWorkoutDetailDataRepository;
import com.rookiefit.back.repository.UserWorkout.UserWorkoutImagesRepository;
import com.rookiefit.back.repository.UserWorkout.UserWorkoutListDataRepository;
import com.rookiefit.back.service.FirebaseService;
import com.rookiefit.back.service.UserWorkoutDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserWorkoutDataServiceImplement implements UserWorkoutDataService{

    private final FirebaseService firebaseService;
    private final UserProfileRepository userProfileRepository;
    private final UserWorkoutListDataRepository userWorkoutListDataRepository;
    private final UserWorkoutDetailDataRepository userWorkoutDetailDataRepository;
    private final UserWorkoutImagesRepository userWorkoutImagesRepository;

    //todo : update와 분리하여 구현할것
    @Transactional
    @Override
    public ResponseEntity<? super InputUserWorkoutListResponseDto> inputUserWorkoutData(InputUserWorkoutListRequestDto dto, String currentUserId) {

        UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
        if(userProfileEntity == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 기존 WorkoutList 데이터 조회
        UserWorkoutListDataEntity userWorkoutListDataEntity = userWorkoutListDataRepository.findByUserIdAndWorkoutCreatedDate(currentUserId, dto.getWorkoutCreatedData());

        if (userWorkoutListDataEntity != null) {
            // 이미 존재하는 경우 데이터 업데이트
            userWorkoutListDataEntity.updateWorkoutData(dto);
            // 기존 WorkoutDetails 삭제 후 새로운 WorkoutDetails 저장
            userWorkoutDetailDataRepository.deleteByUserWorkoutList(userWorkoutListDataEntity);
            userWorkoutListDataEntity.getWorkoutDetails().clear();

            // 기존 WorkoutImages 삭제 (Firebase + DB)
            List<String> existingImageUrls = userWorkoutListDataEntity.getUserWorkoutImages()
                    .stream()
                    .map(UserWorkoutImagesEntity::getWorkoutImageUri)
                    .collect(Collectors.toList());
             // DB에서 이미지 엔티티 삭제
             
        userWorkoutImagesRepository.deleteByUserWorkoutList(userWorkoutListDataEntity);
        userWorkoutListDataEntity.getUserWorkoutImages().clear();


        // Firebase에서 이미지 삭제
        if (!existingImageUrls.isEmpty()) {
            firebaseService.deleteFiles(existingImageUrls);
        }
        } else {
            // 새로운 엔티티 생성
            userWorkoutListDataEntity = new UserWorkoutListDataEntity(dto,currentUserId,userProfileEntity);
            userWorkoutListDataEntity.setUserId(currentUserId);
        }
    
        // WorkoutList 데이터 저장
        userWorkoutListDataRepository.save(userWorkoutListDataEntity);
    
        // 새로운 WorkoutDetails 저장
        userWorkoutListDataEntity.addWorkoutDetails(dto.getWorkoutDetails());
        for (UserWorkoutDetailDataEntity detail : userWorkoutListDataEntity.getWorkoutDetails()) {
            userWorkoutDetailDataRepository.save(detail);
        }

        List<String> workoutImages = new ArrayList<>();
        // 파일 업로드 처리
        if (dto.getWorkoutImageFiles() != null && dto.getWorkoutImageFiles().length > 0) {
            List<MultipartFile> fileList = Arrays.asList(dto.getWorkoutImageFiles());
            try {
                workoutImages = firebaseService.uploadFiles(fileList); // Firebase에 업로드 후 URI 리스트 반환
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }

        // Workout Images 저장
        userWorkoutListDataEntity.addWorkoutImages(workoutImages);
        for (UserWorkoutImagesEntity image : userWorkoutListDataEntity.getUserWorkoutImages()) {
            userWorkoutImagesRepository.save(image);
        }

        return InputUserWorkoutListResponseDto.success();
    }
    
    //241205-11:35_김민준 : @RequestParam으로 교체 완
    @Override
    public ResponseEntity<List<GetUserWorkoutListResponseDto>> getUserWorkoutData(String currentUserId) {
        boolean isExsitedId = userWorkoutListDataRepository.existsByUserId(currentUserId);
        if (!isExsitedId) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 사용자별 운동 리스트 조회
        List<UserWorkoutListDataEntity> userWorkoutListDataEntities = userWorkoutListDataRepository.findWorkoutListByUserId(currentUserId);
        // GetUserWorkoutListResponseDto.success()에서 자동으로 변환 처리됨
        return GetUserWorkoutListResponseDto.success(userWorkoutListDataEntities);
    }


    @Override
    public ResponseEntity<List<GetUserWorkoutDetailResponseDto>> getUserWorkoutDetail(String currentUserId, String workoutDetailCreatedDate) {
        List<UserWorkoutDetailDataEntity> userWorkoutDetailDataEntity = userWorkoutDetailDataRepository.findByUserWorkoutList_UserIdAndWorkoutDetailCreatedDate(currentUserId,workoutDetailCreatedDate);
        return GetUserWorkoutDetailResponseDto.success(userWorkoutDetailDataEntity);  // Pass the entity list to the DTO's success method
    }

    @Override
    public ResponseEntity<? super DeleteUserWorkoutListResponseDto> deleteUserWorkoutList(String currentUserId, String workoutCreatedDate) {
        // 삭제할 운동 목록을 DB에서 찾기
        UserWorkoutListDataEntity userWorkoutListDataEntity = userWorkoutListDataRepository.findByUserIdAndWorkoutCreatedDate(currentUserId, workoutCreatedDate);
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