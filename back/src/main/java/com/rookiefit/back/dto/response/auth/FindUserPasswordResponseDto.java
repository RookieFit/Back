package com.rookiefit.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class FindUserPasswordResponseDto extends ResponseDto{
    
    private FindUserPasswordResponseDto(){
        super();
    }

    public static ResponseEntity<? super FindUserPasswordResponseDto>success(){
        FindUserPasswordResponseDto responseBody = new FindUserPasswordResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto>smsSendFail(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.SMS_FAIL , ResponseMessage.SMS_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto>idNotFound(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.ID_NOT_FOUND , ResponseMessage.ID_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
