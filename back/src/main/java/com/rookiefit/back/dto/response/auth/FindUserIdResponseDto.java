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

    public static ResponseEntity<? super ResponseDto> certificationFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.CERTIFICATION_FAIL, ResponseMessage.CERTIFICATION_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

    public static ResponseEntity<? super ResponseDto> PhoneNumber_NOT_FOUND() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.PhoneNumber_NOT_FOUND,
                ResponseMessage.PhoneNumber_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }
}
