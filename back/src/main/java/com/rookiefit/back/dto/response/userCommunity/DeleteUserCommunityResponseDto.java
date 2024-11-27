package com.rookiefit.back.dto.response.userCommunity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class DeleteUserCommunityResponseDto extends ResponseDto{
    private DeleteUserCommunityResponseDto() {
        super();
    }
    public static ResponseEntity<DeleteUserCommunityResponseDto> success() {
        DeleteUserCommunityResponseDto responseBody = new DeleteUserCommunityResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> communityNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.COMMUNITY_LIST_NOT_FOUND,ResponseMessage.COMMUNITY_LIST_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
