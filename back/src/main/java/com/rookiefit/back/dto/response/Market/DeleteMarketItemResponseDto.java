package com.rookiefit.back.dto.response.Market;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.common.ResponseCode;
import com.rookiefit.back.common.ResponseMessage;
import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class DeleteMarketItemResponseDto extends ResponseDto{
    private DeleteMarketItemResponseDto() {
        super();
    }

    public static ResponseEntity<DeleteMarketItemResponseDto> success() {
        DeleteMarketItemResponseDto responseBody = new DeleteMarketItemResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> marketListNotFound() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.MARKET_LIST_NOT_FOUND,ResponseMessage.MARKET_LIST_NOT_FOUND);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }
}
