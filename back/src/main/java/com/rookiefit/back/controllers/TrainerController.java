package com.rookiefit.back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.rookiefit.back.dto.request.trainer.InputTrainerRequestDto;
import com.rookiefit.back.dto.response.trainer.InputTrainerResponseDto;
import com.rookiefit.back.service.TrainerService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
public class TrainerController {

    private final TrainerService trainerService;

    // 트레이너 등록 요청
    @PostMapping("/register")
    public ResponseEntity<? super InputTrainerResponseDto> createTrainer(
            @RequestBody @Valid InputTrainerRequestDto requestDto) {
        return trainerService.createTrainer(requestDto);
    }

    // 트레이너 승인 처리
    @PutMapping("/approve/{userId}")
    public ResponseEntity<? super InputTrainerResponseDto> approveTrainer(@PathVariable String userId) {
        return trainerService.approveTrainer(userId);
    }
}
