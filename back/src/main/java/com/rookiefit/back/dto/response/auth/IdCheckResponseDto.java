package com.rookiefit.back.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class IdCheckResponseDto extends ResponseDto{
    
    private IdCheckResponseDto() {
        super();
    }

    public static ResponseEntity<IdCheckResponseDto> success() {
        IdCheckResponseDto responseBody = new IdCheckResponseDto( );
        return ResponseEntity.status( HttpStatus.OK ).body(responseBody);
    }
    public static ResponseEntity<ResponseDto> duplicatedId() {
        ResponseDto responseBody = new ResponseDto( ResponseCode.DUPLICATE_ID , ResponseMessage.DUPLICATE_ID );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
