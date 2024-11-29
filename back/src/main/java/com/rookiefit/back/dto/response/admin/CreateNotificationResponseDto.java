package com.rookiefit.back.dto.response.admin;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class CreateNotificationResponseDto extends ResponseDto{
    private CreateNotificationResponseDto() {
        super();
    }
    public static ResponseEntity<CreateNotificationResponseDto> success() {
        CreateNotificationResponseDto reponseBody = new CreateNotificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(reponseBody);
    }
}
