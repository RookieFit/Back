package com.rookiefit.back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.userCommunity.UserCommunityRequestDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityAnswerResponseDto;
import com.rookiefit.back.dto.request.userCommunity.UserCommunityAnswerRequestDto;
import com.rookiefit.back.service.UserCommunityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserCommunityController {
    private final UserCommunityService userCommunityService;

    @PostMapping("/input-usercommunity")
    public ResponseEntity<? super UserCommunityResponseDto> inputUserCommunity(
        @RequestBody @Valid UserCommunityRequestDto dto) {
            ResponseEntity<? super UserCommunityResponseDto> responseBody = userCommunityService.inputUserCommunity(dto);
            return responseBody;
    }

    @PostMapping("/input-usercommunityanswer")
    public ResponseEntity<? super UserCommunityAnswerResponseDto> inputUserCommunityAnswer(
        @RequestBody @Valid UserCommunityAnswerRequestDto dto) {
            ResponseEntity<? super UserCommunityAnswerResponseDto> responseBody = userCommunityService.inputUserCommunityAnswer(dto);
            return responseBody;
    }
}
