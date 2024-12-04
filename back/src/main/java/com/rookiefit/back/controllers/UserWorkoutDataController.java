package com.rookiefit.back.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.response.userWorkoutData.DeleteUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutDetailResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;
import com.rookiefit.back.common.CustomUserDetails;
import com.rookiefit.back.dto.request.userWorkoutData.DeleteUserWorkoutListRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutDetailRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutListRequestDto;
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
        ResponseEntity<? super InputUserWorkoutListResponseDto> responseBody = userWorkoutDataService
                .inputUserWorkoutData(dto);
        return responseBody;
    }

    //todo : 이거 GetMapping으로 바꿔서 requestparam으로 바꿔야함
    @PostMapping("/userworkoutlistdata")
    public ResponseEntity<List<GetUserWorkoutListResponseDto>> getUserWorkoutData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = ((CustomUserDetails) authentication.getPrincipal()).getUserId();
        ResponseEntity<List<GetUserWorkoutListResponseDto>> responseBody = userWorkoutDataService
                .getUserWorkoutData(currentUserId);
        return responseBody;
    }

    //todo : 이거 GetMapping으로 바꿔서 requestparam으로 바꿔야함
    @PostMapping("/userworkoutdetaildata")
    public ResponseEntity<List<GetUserWorkoutDetailResponseDto>> getUserWorkoutDetail(
            @RequestBody @Valid GetUserWorkoutDetailRequestDto dto) {
        ResponseEntity<List<GetUserWorkoutDetailResponseDto>> responseBody = userWorkoutDataService
                .getUserWorkoutDetail(dto);
        return responseBody;
    }

    @DeleteMapping("/delete-userworkoutlistdata")
    public ResponseEntity<? super DeleteUserWorkoutListResponseDto> deleteUserWorkoutList(
            @RequestBody @Valid DeleteUserWorkoutListRequestDto dto) {
        ResponseEntity<? super DeleteUserWorkoutListResponseDto> responseBody = userWorkoutDataService
                .deleteUserWorkoutList(dto);
        return responseBody;
    }
}
