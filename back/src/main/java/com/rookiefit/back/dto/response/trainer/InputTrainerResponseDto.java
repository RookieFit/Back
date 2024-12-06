package com.rookiefit.back.dto.response.trainer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InputTrainerResponseDto {

    private String userId; // 사용자 ID
    private boolean isLicensed; // 라이센스 승인 여부
    private String message;

    public static ResponseEntity<InputTrainerResponseDto> success(String message) {
        InputTrainerResponseDto responseBody = new InputTrainerResponseDto(message);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public InputTrainerResponseDto() {
        super();
    }

    public InputTrainerResponseDto(String message) {
        this.message = message;
    }

    // public InputTrainerResponseDto() {
    // super();
    // }

    // public static ResponseEntity<InputTrainerResponseDto> success() {
    // InputTrainerResponseDto responseBody = new InputTrainerResponseDto();
    // return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    // }
}
