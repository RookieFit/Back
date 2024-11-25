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

    // 트레이너 인증 요청
    @Override
    public ResponseEntity<? super InputTrainerResponseDto> createTrainer(InputTrainerRequestDto dto) {

        // 유저가 존재하는지 확인
        UserEntity userEntity = userRepository.findById(dto.getUserId()).orElse(null);
        if (userEntity == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new InputTrainerResponseDto(dto.getUserId(), false));
        }

        // TrainerEntity 생성 및 저장
        TrainerEntity trainerEntity = new TrainerEntity(dto, userEntity);

        trainerEntity.setUser(userEntity);
        trainerRepository.save(trainerEntity);

        // 응답 생성 및 반환
        InputTrainerResponseDto responseDto = new InputTrainerResponseDto(dto.getUserId(), userEntity.getIsLicensed());
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    // TODO ADMIN 파트 다시 하기..
    // 관리자가 트레이너 요청 승인
    @Override
    public ResponseEntity<? super InputTrainerResponseDto> approveTrainer(String userId) {
        System.out.println("1");

        // 유저가 존재하는지 확인
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));
        System.out.println("2");

        // 라이센스 승인 처리
        userEntity.setIsLicensed(true);
        userRepository.save(userEntity);
        System.out.println("3");

        // 응답 생성 및 반환
        InputTrainerResponseDto responseDto = new InputTrainerResponseDto(userId, true);
        System.out.println("4");
        return ResponseEntity.ok(responseDto);
    }
}
