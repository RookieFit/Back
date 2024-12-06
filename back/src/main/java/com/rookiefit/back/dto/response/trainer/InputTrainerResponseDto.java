package com.rookiefit.back.dto.response.trainer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class InputTrainerResponseDto {

    public InputTrainerResponseDto() {
        super();
    }

    public static ResponseEntity<InputTrainerResponseDto> success() {
        InputTrainerResponseDto responseBody = new InputTrainerResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
