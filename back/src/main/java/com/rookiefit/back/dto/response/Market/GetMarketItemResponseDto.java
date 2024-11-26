package com.rookiefit.back.dto.response.Market;

import java.math.BigDecimal;
import java.util.Date;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.Market.MarketItemListEntity;
import com.rookiefit.back.entity.Market.MarketProductsEntity;

import lombok.Getter;

@Getter
public class GetMarketItemResponseDto extends ResponseDto{

    private String marketItemTitle;
    private String marketItemDescription;
    private String marketItemImageUrl;
    private Date createdAt;
    private boolean isSold;

    // Product 관련 정보
    private BigDecimal productPrice;
    private String productDescription;
    private String location;
    private String saleStatus;

    public GetMarketItemResponseDto(MarketItemListEntity marketItem) {
        this.marketItemTitle = marketItem.getMarketItemTitle();
        this.marketItemImageUrl = marketItem.getMarketItemImageUrl();
        this.createdAt = marketItem.getCreatedAt();
        this.isSold = marketItem.isSold();

        // 상품 정보 가져오기
        MarketProductsEntity product = marketItem.getProduct();
        if (product != null) {
            this.productPrice = product.getProductPrice();
            this.productDescription = product.getProductDescription();
            this.location = product.getLocation();
            this.saleStatus = product.getSaleStatus().name();
        }
    }
}
