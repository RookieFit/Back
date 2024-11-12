package com.rookiefit.back.dto.response.userWorkoutData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class InputUserWorkoutListResponseDto extends ResponseDto {
    private InputUserWorkoutListResponseDto() {
        super();
    }

    public static ResponseEntity<InputUserWorkoutListResponseDto> success() {
        InputUserWorkoutListResponseDto reponseBody = new InputUserWorkoutListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(reponseBody);
    }

}
