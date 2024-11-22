package com.rookiefit.back.service.implement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.trainer.InputTrainerRequestDto;
import com.rookiefit.back.dto.response.trainer.InputTrainerResponseDto;
import com.rookiefit.back.entity.TrainerEntity;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.repository.TrainerRepository;
import com.rookiefit.back.repository.UserRepository;
import com.rookiefit.back.service.TrainerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TrainerServiceImplement implements TrainerService {

    private final TrainerRepository trainerRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<? super InputTrainerResponseDto> createTrainer(InputTrainerRequestDto dto) {
        // 유저가 존재하는지 확인
        UserEntity userEntity = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // // TrainerEntity 생성 및 저장
        // TrainerEntity trainerEntity = new TrainerEntity(
        // null,
        // dto.getUserId(),
        // dto.getLicenseImageUrl(),
        // dto.getLicenseNumber(),
        // dto.getBusinessRegisterNumber(),
        // dto.getBusinessRegisterImageUrl());
        // trainerRepository.save(trainerEntity);

        // 응답 생성 및 반환
        InputTrainerResponseDto responseDto = new InputTrainerResponseDto(dto.getUserId(), userEntity.getIsLicensed());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @Override
    public ResponseEntity<? super InputTrainerResponseDto> approveTrainer(String userId) {
        // 유저 확인 및 라이센스 승인
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        userEntity.approveLicense(); // 라이센스 승인 메서드 호출
        userRepository.save(userEntity);

        // 응답 생성 및 반환
        InputTrainerResponseDto responseDto = new InputTrainerResponseDto(userId, true);
        return ResponseEntity.ok(responseDto);
    }

    // @Override
    // public ResponseEntity<Void> deleteTrainer(Long id) {
    // // 트레이너 확인 및 삭제
    // TrainerEntity trainerEntity = trainerRepository.findById(id)
    // .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 트레이너 ID입니다."));

    // trainerRepository.delete(trainerEntity);

    // // 삭제 성공 응답
    // return ResponseEntity.status(HttpStatus.NO_CONTENT).build
    // }
}