package com.rookiefit.back.dto.response.UserDietData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class InputUserDietListResponseDto extends ResponseDto {
    public InputUserDietListResponseDto() {
        super();
    }

    public static ResponseEntity<InputUserDietListResponseDto> success() {
        InputUserDietListResponseDto reponseBody = new InputUserDietListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(reponseBody);
    }

}
