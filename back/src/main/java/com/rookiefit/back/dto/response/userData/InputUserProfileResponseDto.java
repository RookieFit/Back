package com.rookiefit.back.dto.response.userData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserProfileResponseDto;

import lombok.Getter;

@Getter
public class InputUserProfileResponseDto extends ResponseDto {

    private InputUserProfileResponseDto() {
        super();
    }

    public static ResponseEntity<InputUserProfileResponseDto> succes() {
        InputUserProfileResponseDto responseBody = new InputUserProfileResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
