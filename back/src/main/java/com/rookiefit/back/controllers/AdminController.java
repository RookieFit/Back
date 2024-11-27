package com.rookiefit.back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.rookiefit.back.dto.response.trainer.InputTrainerResponseDto;
import com.rookiefit.back.service.TrainerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminController {

    private final TrainerService trainerService;

    // 트레이너 승인 처리
    /*
     * @PutMapping("/approve/{userId}")
     * 
     * @PreAuthorize("hasRole('ADMIN')")
     * public ResponseEntity<? super InputTrainerResponseDto>
     * approveTrainer(@PathVariable String userId) {
     * // 트레이너 승인 요청 처리
     * return trainerService.approveTrainer(userId);
     * }
     */
}
