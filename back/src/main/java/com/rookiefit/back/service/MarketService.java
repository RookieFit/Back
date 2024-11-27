package com.rookiefit.back.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.Market.MarketItemListRequestDto;
import com.rookiefit.back.dto.response.Market.DeleteMarketItemResponseDto;
import com.rookiefit.back.dto.response.Market.GetAllMarketItemListResponseDto;
import com.rookiefit.back.dto.response.Market.GetMarketItemResponseDto;
import com.rookiefit.back.dto.response.Market.InputMarketItemListResponseDto;

public interface MarketService {
    ResponseEntity<? super InputMarketItemListResponseDto> inputMarketItemList(MarketItemListRequestDto dto);
    ResponseEntity<List<GetAllMarketItemListResponseDto>> getAllMarketItemList();
    ResponseEntity<List<GetAllMarketItemListResponseDto>> getBySaleStatusMarketList(String saleStatus);
    ResponseEntity<List<GetAllMarketItemListResponseDto>> getByKeywordMarketList(String keyword, String field);
    ResponseEntity<? super GetMarketItemResponseDto> getMarketItem(Long id);
    ResponseEntity<? super DeleteMarketItemResponseDto> deleteMarketItem(Long id);
    ResponseEntity<? super InputMarketItemListResponseDto> updateMarketItemList(MarketItemListRequestDto dto, Long marketListId);
}