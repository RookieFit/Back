package com.rookiefit.back.dto.response.userWorkoutData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class InputUserWorkoutDetailResponseDto extends ResponseDto{
    private InputUserWorkoutDetailResponseDto() {
        super();
    }

    public static ResponseEntity<InputUserWorkoutDetailResponseDto> success() {
        InputUserWorkoutDetailResponseDto responseBody = new InputUserWorkoutDetailResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
