package com.rookiefit.back.dto.response.trainer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputTrainerResponseDto {

    private String userId;

    private boolean isLicensed;

    public static ResponseEntity<InputTrainerResponseDto> success(String userId, boolean isLicensed) {
        InputTrainerResponseDto responseBody = new InputTrainerResponseDto(userId, isLicensed);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
