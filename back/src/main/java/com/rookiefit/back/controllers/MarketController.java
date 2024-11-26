package com.rookiefit.back.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.Market.MarketItemListRequestDto;
import com.rookiefit.back.dto.response.Market.InputMarketItemListResponseDto;
import com.rookiefit.back.service.MarketService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class MarketController {

    private final MarketService marketService;

    @PostMapping("/input-marketlist")
    public ResponseEntity<? super InputMarketItemListResponseDto> inputMarketItemList(
        @RequestBody @Valid MarketItemListRequestDto dto) {
            ResponseEntity<? super InputMarketItemListResponseDto> responseBody = marketService.inputMarketItemList(dto);
            return responseBody;
    }
}
