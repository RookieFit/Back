package com.rookiefit.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FindUserIdResponseDto extends ResponseDto {

    public FindUserIdResponseDto() {
        super();
    }

    public static ResponseEntity<? super FindUserIdResponseDto> success() {
        FindUserIdResponseDto responseBody = new FindUserIdResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> smsSendFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SMS_FAIL, ResponseMessage.SMS_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> PhoneNumber_NOT_FOUND() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.PHONENUMBER_NOT_FOUND,
                ResponseMessage.PHONENUMBER_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
