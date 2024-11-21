package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userCommunity.UserCommunityAnswerRequestDto;
import com.rookiefit.back.dto.request.userCommunity.UserCommunityRequestDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityAnswerResponseDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityResponseDto;

public interface UserCommunityService {
    ResponseEntity<? super UserCommunityResponseDto> inputUserCommunity(UserCommunityRequestDto dto);
    ResponseEntity<? super UserCommunityAnswerResponseDto> inputUserCommunityAnswer(UserCommunityAnswerRequestDto dto);
}
