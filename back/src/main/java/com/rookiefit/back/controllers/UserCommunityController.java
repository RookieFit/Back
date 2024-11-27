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
import com.rookiefit.back.dto.response.Market.GetMarketItemResponseDto;
import com.rookiefit.back.dto.response.userCommunity.DeleteUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetAllUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetByContentTypeUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetSearchUserCommunityResponseDto;
import com.rookiefit.back.service.UserCommunityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/input-usercommunity-answer")
    public ResponseEntity<? super UserCommunityAnswerResponseDto> inputUserCommunityAnswer(
        @RequestBody @Valid UserCommunityAnswerRequestDto dto) {
            ResponseEntity<? super UserCommunityAnswerResponseDto> responseBody = userCommunityService.inputUserCommunityAnswer(dto);
            return responseBody;
    }

    @GetMapping("/get-all-usercommunity")
    public ResponseEntity<? super GetAllUserCommunityResponseDto> getAllUserCommunity(){
            ResponseEntity<? super GetAllUserCommunityResponseDto> responseBody = userCommunityService.getAllUserCommunity();
            return responseBody;
    }

    @GetMapping("/get-bycontenttype-usercommunity")
    public ResponseEntity<List<GetByContentTypeUserCommunityResponseDto>> getByContentTypeUserCommunity(
        @RequestParam(required = false,value = "communityContentType") String communityContentType){
            ResponseEntity<List<GetByContentTypeUserCommunityResponseDto>> responseBody = userCommunityService.getByContentTypeUserCommunity(communityContentType);
            return responseBody;
    }

    @GetMapping("/getusercommunity/{id}")
    public ResponseEntity<? super GetUserCommunityResponseDto> getUserCommunity(@PathVariable("id") Long id) {
        ResponseEntity<? super GetUserCommunityResponseDto> responseBody = userCommunityService.getUserCommunity(id);
        return responseBody;
    }

    @DeleteMapping("/delete-usercommunity")
    public ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunity(
        @RequestParam(required = false,value = "communityListId") Long communityListId) {
            ResponseEntity<? super DeleteUserCommunityResponseDto> responseBody = userCommunityService.deleteUserCommunity(communityListId);
            return responseBody;
    }

    @DeleteMapping("/delete-usercommunity-answer")
    public ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunityAnswer(
        @RequestParam(required = false,value = "communityAnswerListId") Long communityAnswerListId) {
            ResponseEntity<? super DeleteUserCommunityResponseDto> responseBody = userCommunityService.deleteUserCommunityAnswer(communityAnswerListId);
            return responseBody;
    }

    @GetMapping("/communitysearch")
    public ResponseEntity<List<GetSearchUserCommunityResponseDto>> getSearchUserCommunity(
        @RequestParam(required = false,value = "keyword") String keyword,
        @RequestParam(required = false,value = "field") String field
         ) {
            ResponseEntity<List<GetSearchUserCommunityResponseDto>> responseBody = userCommunityService.getSearchUserCommunity(keyword,field);
            return responseBody;
    }
}
