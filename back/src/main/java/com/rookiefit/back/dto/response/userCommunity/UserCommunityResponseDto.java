package com.rookiefit.back.dto.response.userCommunity;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class UserCommunityResponseDto extends ResponseDto {

    private UserCommunityResponseDto() {
        super();
    }

    public static ResponseEntity<UserCommunityResponseDto> success() {
        UserCommunityResponseDto responseBody = new UserCommunityResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> idNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.ID_NOT_FOUND, ResponseMessage.ID_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> communityListIdNotFound() {
        ResponseDto responeBody = new ResponseDto(ResponseCode.COMMUNITY_LIST_NOT_FOUND,
                ResponseMessage.COMMUNITY_LIST_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responeBody);
    }
}
