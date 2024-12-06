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

        // 유저가 존재하는지 확인 (로그인된 유저만 처리)
        UserEntity userEntity = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // TrainerEntity 생성 및 저장
        TrainerEntity trainerEntity = new TrainerEntity(dto, userEntity);

        trainerEntity.setUser(userEntity);
        trainerRepository.save(trainerEntity);

        // 응답 생성 및 반환 (인증 신청 완료 응답)
        InputTrainerResponseDto responseDto = new InputTrainerResponseDto();
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
