package com.rookiefit.back.dto.response.UserDietData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class DeleteUserDietListResponseDto extends ResponseDto {

    private DeleteUserDietListResponseDto() {
        super();
    }

    public static ResponseEntity<? super DeleteUserDietListResponseDto> success() {
        DeleteUserDietListResponseDto responseBody = new DeleteUserDietListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> deleteFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.DATABASE_DELETE_FAIL,
                ResponseMessage.DATABASE_DELETE_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
