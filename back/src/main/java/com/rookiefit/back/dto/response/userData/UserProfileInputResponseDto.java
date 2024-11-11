package com.rookiefit.back.dto.response.userData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.userData.UserProfileInputResponseDto;

import lombok.Getter;

@Getter
public class UserProfileInputResponseDto extends ResponseDto {

    private UserProfileInputResponseDto() {
        super();
    }

    public static ResponseEntity<UserProfileInputResponseDto> succes() {
        UserProfileInputResponseDto responseBody = new UserProfileInputResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
