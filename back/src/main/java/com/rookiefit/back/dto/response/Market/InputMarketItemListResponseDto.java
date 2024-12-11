package com.rookiefit.back.dto.response.Market;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class InputMarketItemListResponseDto extends ResponseDto {
    private InputMarketItemListResponseDto() {
        super();
    }

    public static ResponseEntity<InputMarketItemListResponseDto> success() {
        InputMarketItemListResponseDto responseBody = new InputMarketItemListResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
