package com.rookiefit.back.dto.response.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class InputNotificationResponseDto extends ResponseDto{
    private InputNotificationResponseDto() {
        super();
    }
    public static ResponseEntity<InputNotificationResponseDto> success() {
        InputNotificationResponseDto reponseBody = new InputNotificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(reponseBody);
    }
}
