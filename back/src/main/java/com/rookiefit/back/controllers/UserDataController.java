package com.rookiefit.back.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserBodyDataRequestDto;
import com.rookiefit.back.dto.response.userData.InputUserProfileResponseDto;
import com.rookiefit.back.dto.response.userData.GetUserBodyDataResponseDto;
import com.rookiefit.back.dto.response.userData.GetUserProfileResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserBodyDataResponseDto;
import com.rookiefit.back.service.UserDataService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserDataController {
    private final UserDataService userDataService;

    @PostMapping("/input-userprofile")
    public ResponseEntity<? super InputUserProfileResponseDto> inputUserProfile(
            @ModelAttribute InputUserProfileRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String) authentication.getPrincipal();
        ResponseEntity<? super InputUserProfileResponseDto> responseBody = userDataService.inputUserProfile(dto,currentUserId);
        return responseBody;
    }
    
    @GetMapping("/userprofile")
    public ResponseEntity<? super GetUserProfileResponseDto> userProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String) authentication.getPrincipal();
        ResponseEntity<? super GetUserProfileResponseDto> responseBody = userDataService.getUserProfile(currentUserId);
        return responseBody;
    }

    @PostMapping("/input-userbodydata")
    public ResponseEntity<? super InputUserBodyDataResponseDto> inputUserBodyData(
            @RequestBody @Valid InputUserBodyDataRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String) authentication.getPrincipal();
        ResponseEntity<? super InputUserBodyDataResponseDto> responseBody = userDataService.inputUserBodyData(dto,currentUserId);
        return responseBody;
    }

    @GetMapping("/userbodydata")
    public ResponseEntity<List<GetUserBodyDataResponseDto>> getUserBodyData() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String) authentication.getPrincipal();
        ResponseEntity<List<GetUserBodyDataResponseDto>> reponseBody = userDataService.getUserBodyData(currentUserId);
        return reponseBody;
    }
}
