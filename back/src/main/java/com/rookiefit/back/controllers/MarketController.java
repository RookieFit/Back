package com.rookiefit.back.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.request.Market.MarketItemListRequestDto;
import com.rookiefit.back.dto.response.Market.DeleteMarketItemResponseDto;
import com.rookiefit.back.dto.response.Market.GetAllMarketItemListResponseDto;
import com.rookiefit.back.dto.response.Market.GetMarketItemResponseDto;
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
            @ModelAttribute @Valid MarketItemListRequestDto dto) {
        ResponseEntity<? super InputMarketItemListResponseDto> responseBody = marketService.inputMarketItemList(dto);
        return responseBody;
    }

    @PutMapping("/updatemarketlist/{id}")
    public ResponseEntity<? super InputMarketItemListResponseDto> updateMarketItemList(
            @PathVariable("id") Long marketListId, @ModelAttribute MarketItemListRequestDto dto) {
        ResponseEntity<? super InputMarketItemListResponseDto> responseBody = marketService.updateMarketItemList(dto,
                marketListId);
        return responseBody;
    }

    @GetMapping("/getallmarketlist")
    public ResponseEntity<List<GetAllMarketItemListResponseDto>> getAllMarketItemList() {
        ResponseEntity<List<GetAllMarketItemListResponseDto>> responseBody = marketService.getAllMarketItemList();
        return responseBody;
    }

    @GetMapping("/getmarketlistbysalestatus")
    public ResponseEntity<List<GetAllMarketItemListResponseDto>> getBySaleStatusMarketList(
            @RequestParam(required = false, value = "saleStatus") String saleStatus) {
        ResponseEntity<List<GetAllMarketItemListResponseDto>> responseBody = marketService
                .getBySaleStatusMarketList(saleStatus);
        return responseBody;
    }

    @GetMapping("/getmarketitem/{id}")
    public ResponseEntity<? super GetMarketItemResponseDto> getMarketItem(@PathVariable("id") Long id) {
        ResponseEntity<? super GetMarketItemResponseDto> responseBody = marketService.getMarketItem(id);
        return responseBody;
    }

    @GetMapping("/marketlistsearch")
    public ResponseEntity<List<GetAllMarketItemListResponseDto>> getByKeywordMarketList(
            @RequestParam(required = false, value = "keyword") String keyword,
            @RequestParam(required = false, value = "field") String field) {
        ResponseEntity<List<GetAllMarketItemListResponseDto>> responseBody = marketService
                .getByKeywordMarketList(keyword, field);
        return responseBody;
    }

    @DeleteMapping("/deletemarketitem/{id}")
    public ResponseEntity<? super DeleteMarketItemResponseDto> deleteMarketItem(@PathVariable("id") Long id) {
        ResponseEntity<? super DeleteMarketItemResponseDto> responseBody = marketService.deleteMarketItem(id);
        return responseBody;
    }
}