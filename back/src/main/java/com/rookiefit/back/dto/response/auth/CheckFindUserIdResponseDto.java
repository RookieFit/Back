package com.rookiefit.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class CheckFindUserIdResponseDto extends ResponseDto {

    private CheckFindUserIdResponseDto() {
        super();
    }

    public static ResponseEntity<CheckFindUserIdResponseDto> success() {
        CheckFindUserIdResponseDto responseBody = new CheckFindUserIdResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<? super CheckFindUserIdResponseDto> certificationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
