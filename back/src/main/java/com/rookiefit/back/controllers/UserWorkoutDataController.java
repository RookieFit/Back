package com.rookiefit.back.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.response.userWorkoutData.DeleteUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutDetailResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.service.UserWorkoutDataService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserWorkoutDataController {
    private final UserWorkoutDataService userWorkoutDataService;

    @PostMapping("/input-userworkoutlistdata")
    public ResponseEntity<? super InputUserWorkoutListResponseDto> inputUserWorkoutData(
        @ModelAttribute @Valid InputUserWorkoutListRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String) authentication.getPrincipal();
        ResponseEntity<? super InputUserWorkoutListResponseDto> responseBody = userWorkoutDataService
                .inputUserWorkoutData(dto,currentUserId);
        return responseBody;
    }

    @GetMapping("/userworkoutlistdata")
    public ResponseEntity<List<GetUserWorkoutListResponseDto>> getUserWorkoutData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String) authentication.getPrincipal();
        ResponseEntity<List<GetUserWorkoutListResponseDto>> responseBody = userWorkoutDataService
                .getUserWorkoutData(currentUserId);
        return responseBody;
    }

    //241205-12:43_김민준 : @RequesParam 변경 완
    @GetMapping("/userworkoutdetaildata")
    public ResponseEntity<List<GetUserWorkoutDetailResponseDto>> getUserWorkoutDetail(
            @RequestParam("workoutDetailCreatedDate") String workoutDetailCreatedDate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String) authentication.getPrincipal();
        ResponseEntity<List<GetUserWorkoutDetailResponseDto>> responseBody = userWorkoutDataService
                .getUserWorkoutDetail(currentUserId,workoutDetailCreatedDate);
        return responseBody;
    }

    //241205-12:43_김민준 : @RequesParam 변경 완
    @DeleteMapping("/delete-userworkoutlistdata")
    public ResponseEntity<? super DeleteUserWorkoutListResponseDto> deleteUserWorkoutList(
            @RequestParam("workoutCreatedDate") String workoutCreatedDate) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String) authentication.getPrincipal();
        ResponseEntity<? super DeleteUserWorkoutListResponseDto> responseBody = userWorkoutDataService
                .deleteUserWorkoutList(currentUserId,workoutCreatedDate);
        return responseBody;
    }
}
