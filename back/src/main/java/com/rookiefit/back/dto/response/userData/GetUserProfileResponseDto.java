package com.rookiefit.back.dto.response.userData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class GetUserProfileResponseDto extends ResponseDto{
    private GetUserProfileResponseDto() {
        super();
    }

    public static ResponseEntity<GetUserProfileResponseDto> success() {
        GetUserProfileResponseDto responseBody = new GetUserProfileResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
