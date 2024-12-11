package com.rookiefit.back.dto.response.UserDietData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class InputFoodInfoResponseDto extends ResponseDto {
    public InputFoodInfoResponseDto() {
        super();
    }

    public static ResponseEntity<InputFoodInfoResponseDto> success() {
        InputFoodInfoResponseDto responseBody = new InputFoodInfoResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> insertFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.FOODINFO_INSERT_FAIL,
                ResponseMessage.FOODINFO_INSERT_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}