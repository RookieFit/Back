package com.rookiefit.back.dto.response.auth;

import org.hibernate.validator.cfg.defs.pl.REGONDef;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class SignInResponseDto extends ResponseDto{
    

    private SignInResponseDto() {
        super();
    }

     public static ResponseEntity<SignInResponseDto> success() {
        SignInResponseDto responseBody = new SignInResponseDto( );
        return ResponseEntity.status( HttpStatus.OK ).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.Sign_IN_FAIL , ResponseMessage.Sign_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
