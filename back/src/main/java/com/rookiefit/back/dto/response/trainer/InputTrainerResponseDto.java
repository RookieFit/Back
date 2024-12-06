package com.rookiefit.back.dto.response.trainer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Getter;

@Getter
public class InputTrainerResponseDto {

    private String message;

    // 기본 생성자
    public InputTrainerResponseDto() {
        super();
    }

    // 메시지를 받는 생성자
    public InputTrainerResponseDto(String message) {
        this.message = message;
    }

    // 성공 응답을 반환하는 static 메소드
    public static ResponseEntity<InputTrainerResponseDto> success(String message) {
        InputTrainerResponseDto responseBody = new InputTrainerResponseDto(message);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }
}
