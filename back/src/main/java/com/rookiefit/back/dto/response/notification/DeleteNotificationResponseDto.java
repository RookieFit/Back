package com.rookiefit.back.dto.response.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class DeleteNotificationResponseDto extends ResponseDto{
    private DeleteNotificationResponseDto() {
        super();
    }

    public static ResponseEntity<DeleteNotificationResponseDto> success() {
        DeleteNotificationResponseDto responseBody = new DeleteNotificationResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notificatonNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOTIFICATION_NOT_FOUND,ResponseMessage.NOTIFICATION_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
