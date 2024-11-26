package com.rookiefit.back.service;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.Market.MarketItemListRequestDto;
import com.rookiefit.back.dto.response.Market.InputMarketItemListResponseDto;

public interface MarketService {
    ResponseEntity<? super InputMarketItemListResponseDto> inputMarketItemList(MarketItemListRequestDto dto);
}
