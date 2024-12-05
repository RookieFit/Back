package com.rookiefit.back.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.userCommunity.UserCommunityRequestDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityAnswerResponseDto;
import com.rookiefit.back.dto.request.userCommunity.UserCommunityAnswerRequestDto;
import com.rookiefit.back.dto.response.userCommunity.DeleteUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetAllUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetByContentTypeUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetSearchUserCommunityResponseDto;
import com.rookiefit.back.service.UserCommunityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserCommunityController {
    private final UserCommunityService userCommunityService;

    //241205-14:03_김민준 : token을 body로 받는게 아닌 Authentication에서 가져오기 추가 완
    @PostMapping("/input-usercommunity")
    public ResponseEntity<? super UserCommunityResponseDto> inputUserCommunity(
            @ModelAttribute @Valid UserCommunityRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String)authentication.getPrincipal();
        ResponseEntity<? super UserCommunityResponseDto> responseBody = userCommunityService.inputUserCommunity(dto,currentUserId);
        return responseBody;
    }

    //241205-14:03_김민준 : token을 body로 받는게 아닌 Authentication에서 가져오기 추가 완
    @PostMapping("/input-usercommunity-answer")
    public ResponseEntity<? super UserCommunityAnswerResponseDto> inputUserCommunityAnswer(
            @RequestBody @Valid UserCommunityAnswerRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String)authentication.getPrincipal();
        ResponseEntity<? super UserCommunityAnswerResponseDto> responseBody = userCommunityService
                .inputUserCommunityAnswer(dto,currentUserId);
        return responseBody;
    }

    //241205-14:03_김민준 : token을 body로 받는게 아닌 Authentication에서 가져오기 추가 완
    @PutMapping("/update-usercommunity/{id}")
    public ResponseEntity<? super UserCommunityResponseDto> updateUserCommunity( 
        @PathVariable("id") Long userCommunityId, 
        @ModelAttribute @Valid UserCommunityRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String)authentication.getPrincipal();
        ResponseEntity<? super UserCommunityResponseDto> responseBody = userCommunityService.updateUserCommunity(dto,userCommunityId,currentUserId);
        return responseBody;
    }

    @GetMapping("/get-all-usercommunity")
    public ResponseEntity<List<GetAllUserCommunityResponseDto>> getAllUserCommunity() {
        return userCommunityService.getAllUserCommunity();
    }

    @GetMapping("/get-bycontenttype-usercommunity")
    public ResponseEntity<List<GetByContentTypeUserCommunityResponseDto>> getByContentTypeUserCommunity(
            @RequestParam(required = false, value = "communityContentType") String communityContentType) {
        ResponseEntity<List<GetByContentTypeUserCommunityResponseDto>> responseBody = userCommunityService
                .getByContentTypeUserCommunity(communityContentType);
        return responseBody;
    }

    @GetMapping("/getusercommunity/{id}")
    public ResponseEntity<? super GetUserCommunityResponseDto> getUserCommunity(@PathVariable("id") Long id) {
        ResponseEntity<? super GetUserCommunityResponseDto> responseBody = userCommunityService.getUserCommunity(id);
        return responseBody;
    }

    @DeleteMapping("/delete-usercommunity")
    public ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunity(
            @RequestParam(required = false, value = "communityListId") Long communityListId) {
        ResponseEntity<? super DeleteUserCommunityResponseDto> responseBody = userCommunityService
                .deleteUserCommunity(communityListId);
        return responseBody;
    }

    @DeleteMapping("/delete-usercommunity-answer")
    public ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunityAnswer(
            @RequestParam(required = false, value = "communityAnswerListId") Long communityAnswerListId) {
        ResponseEntity<? super DeleteUserCommunityResponseDto> responseBody = userCommunityService
                .deleteUserCommunityAnswer(communityAnswerListId);
        return responseBody;
    }

    @GetMapping("/communitysearch")
    public ResponseEntity<List<GetSearchUserCommunityResponseDto>> getSearchUserCommunity(
            @RequestParam(required = false, value = "keyword") String keyword,
            @RequestParam(required = false, value = "field") String field) {
        ResponseEntity<List<GetSearchUserCommunityResponseDto>> responseBody = userCommunityService
                .getSearchUserCommunity(keyword, field);
        return responseBody;
    }
}
