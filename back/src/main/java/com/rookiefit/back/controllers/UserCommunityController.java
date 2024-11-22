package com.rookiefit.back.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.rookiefit.back.dto.response.userCommunity.GetSearchUserCommunityResponseDto;
import com.rookiefit.back.service.UserCommunityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


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

    @GetMapping("/get-allusercommunity")
    public ResponseEntity<? super GetAllUserCommunityResponseDto> getAllUserCommunity(){
            ResponseEntity<? super GetAllUserCommunityResponseDto> responseBody = userCommunityService.getAllUserCommunity();
            return responseBody;
    }

    @GetMapping("/get-usercommunity")
    public ResponseEntity<List<GetUserCommunityResponseDto>> getUserCommunity(
        @RequestParam(required = false,value = "communityContentType") String communityContentType){
            ResponseEntity<List<GetUserCommunityResponseDto>> responseBody = userCommunityService.getUserCommunity(communityContentType);
            return responseBody;
    }

    @DeleteMapping("/delete-usercommunity")
    public ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunity(
        @RequestParam(required = false,value = "communityListId") Long communityListId) {
            ResponseEntity<? super DeleteUserCommunityResponseDto> responseBody = userCommunityService.deleteUserCommunity(communityListId);
            return responseBody;
    }

    @DeleteMapping("/delete-usercommunityanswer")
    public ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunityAnswer(
        @RequestParam(required = false,value = "communityAnswerListId") Long communityAnswerListId) {
            ResponseEntity<? super DeleteUserCommunityResponseDto> responseBody = userCommunityService.deleteUserCommunityAnswer(communityAnswerListId);
            return responseBody;
    }

    @GetMapping("/search")
    public ResponseEntity<List<GetSearchUserCommunityResponseDto>> getSearchUserCommunity(
        @RequestParam(required = false,value = "keyword") String keyword,
        @RequestParam(required = false,value = "field") String field
         ) {
            ResponseEntity<List<GetSearchUserCommunityResponseDto>> responseBody = userCommunityService.getSearchUserCommunity(keyword,field);
            return responseBody;
    }
}
