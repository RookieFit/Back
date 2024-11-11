package com.rookiefit.back.dto.response.userData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class InputUserBodyDataResponseDto extends ResponseDto {
    
    private InputUserBodyDataResponseDto() {
        super();
    }

    public static ResponseEntity<InputUserBodyDataResponseDto> success() {
        InputUserBodyDataResponseDto responseBody = new InputUserBodyDataResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
