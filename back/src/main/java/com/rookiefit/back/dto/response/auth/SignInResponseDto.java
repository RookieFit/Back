package com.rookiefit.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class SignInResponseDto extends ResponseDto{

    private String token;
    private int expirationTime;
    
    private SignInResponseDto( String token ) {
        super();
        this.token = token;
        this.expirationTime = 3600;
    }

     public static ResponseEntity<SignInResponseDto> success( String token ) {
        SignInResponseDto responseBody = new SignInResponseDto(token );
        return ResponseEntity.status( HttpStatus.OK ).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.Sign_IN_FAIL , ResponseMessage.Sign_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}
