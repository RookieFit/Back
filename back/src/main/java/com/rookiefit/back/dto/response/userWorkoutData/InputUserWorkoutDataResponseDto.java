package com.rookiefit.back.dto.response.userWorkoutData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class InputUserWorkoutDataResponseDto extends ResponseDto {
    private InputUserWorkoutDataResponseDto() {
        super();
    }

    public static ResponseEntity<InputUserWorkoutDataResponseDto> success() {
        InputUserWorkoutDataResponseDto reponseBody = new InputUserWorkoutDataResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(reponseBody);
    }

}
