package com.rookiefit.back.dto.response.UserDietData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.auth.SignUpResponseDto;

import lombok.Getter;

@Getter
public class InputUserDietListResponseDto extends ResponseDto {
    public InputUserDietListResponseDto() {
        super();
    }

    public static ResponseEntity<? super InputUserDietListResponseDto> success() {
        SignUpResponseDto responseBody = new SignUpResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
