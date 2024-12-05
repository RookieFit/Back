package com.rookiefit.back.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.userCommunity.UserCommunityAnswerRequestDto;
import com.rookiefit.back.dto.request.userCommunity.UserCommunityRequestDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityAnswerResponseDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.DeleteUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetAllUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetSearchUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetByContentTypeUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetUserCommunityResponseDto;

public interface UserCommunityService {
    ResponseEntity<? super UserCommunityResponseDto> inputUserCommunity(UserCommunityRequestDto dto, String currentUserId);
    ResponseEntity<? super UserCommunityAnswerResponseDto> inputUserCommunityAnswer(UserCommunityAnswerRequestDto dto, String currentUserId);
    ResponseEntity<? super UserCommunityResponseDto> updateUserCommunity(UserCommunityRequestDto dto, Long userCommunityId, String currentUserId);
    ResponseEntity<List<GetAllUserCommunityResponseDto>> getAllUserCommunity();
    ResponseEntity<List<GetByContentTypeUserCommunityResponseDto>> getByContentTypeUserCommunity(String communityContentType);
    ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunity(Long communityListId);
    ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunityAnswer(Long communityAnswerListId);
    ResponseEntity<List<GetSearchUserCommunityResponseDto>> getSearchUserCommunity(String keyword, String field);
    ResponseEntity<? super GetUserCommunityResponseDto> getUserCommunity(Long id);
}
