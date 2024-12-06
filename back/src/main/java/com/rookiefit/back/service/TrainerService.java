package com.rookiefit.back.service;

import com.rookiefit.back.dto.request.trainer.InputTrainerRequestDto;
import com.rookiefit.back.dto.response.trainer.InputTrainerResponseDto;

import org.springframework.http.ResponseEntity;

public interface TrainerService {

    // 트레이너 인증 요청 등록
    ResponseEntity<? super InputTrainerResponseDto> createTrainer(InputTrainerRequestDto dto, String currentUserId);

    // 관리자 승인 후 라이센스 승인 처리
    // ResponseEntity<? super InputTrainerResponseDto> approveTrainer(String
    // userId);

    // 트레이너 정보 삭제
    // ResponseEntity<? super DeleteTrainerResponseDto>
    // deleteTrainer(DeleteTrainerRequestDto dto);
}
