package com.rookiefit.back.dto.response.trainer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class InputTrainerResponseDto {

    private boolean isLicensed; // 라이센스 승인 여부
    private String message;
    
    public InputTrainerResponseDto() {
        super();
    }

    public InputTrainerResponseDto(String message) {
        this.message = message;
    }


    // 성공 응답을 반환하는 static 메소드
    public static ResponseEntity<InputTrainerResponseDto> success(String message) {
        InputTrainerResponseDto responseBody = new InputTrainerResponseDto(message);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
