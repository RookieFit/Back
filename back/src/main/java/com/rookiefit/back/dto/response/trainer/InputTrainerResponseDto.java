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

    public static InputTrainerResponseDto success(String userId, boolean isLicensed) {
        return new InputTrainerResponseDto(userId, isLicensed);
    }

    // public InputTrainerResponseDto() {
    // super();
    // }

    // public static ResponseEntity<InputTrainerResponseDto> success() {
    // InputTrainerResponseDto responseBody = new InputTrainerResponseDto();
    // return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    // }
}
