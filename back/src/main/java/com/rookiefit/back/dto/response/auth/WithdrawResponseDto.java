package com.rookiefit.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

public class WithdrawResponseDto extends ResponseDto {

    private WithdrawResponseDto() {
        super();
    }

    public static ResponseEntity<WithdrawResponseDto> success() {
        WithdrawResponseDto responseBody = new WithdrawResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> passwordMismatch() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.PASSWORD_MISMATCH, ResponseMessage.PASSWORD_MISMATCH);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
