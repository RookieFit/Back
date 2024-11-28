package com.rookiefit.back.dto.response.Market;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.Market.MarketItemListEntity;
import com.rookiefit.back.entity.Market.MarketProductsEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GetAllMarketItemListResponseDto extends ResponseDto {

    @NotBlank
    private String marketItemTitle;

    private String marketItemImageUrl;

    @NotNull
    private Date createdAt;

    private Date updatedAt;

    private boolean isSold;

    private BigDecimal productPrice;

    private String location;

    public GetAllMarketItemListResponseDto(MarketItemListEntity marketItemListEntity,
            MarketProductsEntity marketProductsEntity) {
        this.marketItemTitle = marketItemListEntity.getMarketItemTitle();
        this.marketItemImageUrl = marketItemListEntity.getMarketItemImageUrl();
        this.createdAt = marketItemListEntity.getCreatedAt();
        this.updatedAt = marketItemListEntity.getUpdatedAt();
        this.isSold = marketItemListEntity.isSold();
        this.productPrice = marketProductsEntity.getProductPrice();
        this.location = marketProductsEntity.getLocation();
    }

    public static List<GetAllMarketItemListResponseDto> fromEntityList(List<MarketItemListEntity> entities) {
        return entities.stream()
                .map(entity -> new GetAllMarketItemListResponseDto(entity, entity.getProduct()))
                .toList();
    }
}
