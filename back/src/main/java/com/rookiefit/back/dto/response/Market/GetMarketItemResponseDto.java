package com.rookiefit.back.dto.response.Market;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.Market.ItemImageEntity;
import com.rookiefit.back.entity.Market.MarketItemListEntity;
import com.rookiefit.back.entity.Market.MarketProductsEntity;

import lombok.Getter;

@Getter
public class GetMarketItemResponseDto extends ResponseDto{

    private String marketItemTitle;
    private String marketItemDescription;
    private String marketItemImageUrl;
    private String createdAt;
    private String updatedAt;
    private boolean isSold;

    // Product 관련 정보
    private BigDecimal productPrice;
    private String productDescription;
    private String location;
    private String saleStatus;

    // itemimage 관련 정보
    private List<String> imageUris;

    public GetMarketItemResponseDto(MarketItemListEntity marketItem) {
        this.marketItemTitle = marketItem.getMarketItemTitle();
        this.marketItemImageUrl = marketItem.getMarketItemImageUrl();
        this.createdAt = marketItem.getCreatedAt();
        this.updatedAt = marketItem.getUpdatedAt();
        this.isSold = marketItem.isSold();
        this.imageUris = marketItem.getItemImages().stream()
            .map(ItemImageEntity::getItemImageUri)
            .collect(Collectors.toList());

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
