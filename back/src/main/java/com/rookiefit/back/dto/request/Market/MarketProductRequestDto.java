package com.rookiefit.back.dto.request.Market;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketProductRequestDto {
    private Long marketListId; // MarketItemList ID
    private BigDecimal productPrice; // 제품 가격
    private String location; // 제품 위치
    private String productDescription;
    //private Long categoryId; // 카테고리 ID
    private String shippingMethod; // 배송 방법 (direct, delivery, both)
    private String productCondition; // 제품 상태 (NEW, USED, LIKE_NEW, REFURBISHED)
    private String saleStatus; // 판매여부 (SELLING, BUYING, SOLD, BOUGHT)
    private String productSeller;
    private String productCreatedAt; // Date 타입에서 String 타입으로 변경
    private String productUpdatedAt; // Date 타입에서 String 타입으로 변경
}
