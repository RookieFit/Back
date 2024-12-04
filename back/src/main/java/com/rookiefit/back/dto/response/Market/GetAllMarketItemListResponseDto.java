package com.rookiefit.back.dto.response.Market;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.Market.ItemImageEntity;
import com.rookiefit.back.entity.Market.MarketItemListEntity;
import com.rookiefit.back.entity.Market.MarketProductsEntity;
import com.rookiefit.back.entity.UserWorkout.UserWorkoutImagesEntity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class GetAllMarketItemListResponseDto extends ResponseDto {

    @NotBlank
    private String marketItemTitle;

    private String marketItemImageUrl;

    @NotNull
    private String createdAt;

    private String updatedAt;

    private boolean isSold;

    private BigDecimal productPrice;

    private String location;

    private List<String> imageUris;

    public GetAllMarketItemListResponseDto(MarketItemListEntity marketItemListEntity,
            MarketProductsEntity marketProductsEntity, List<ItemImageEntity> imageEntities) {
        this.marketItemTitle = marketItemListEntity.getMarketItemTitle();
        this.marketItemImageUrl = marketItemListEntity.getMarketItemImageUrl();
        this.createdAt = marketItemListEntity.getCreatedAt();
        this.updatedAt = marketItemListEntity.getUpdatedAt();
        this.isSold = marketItemListEntity.isSold();
        this.productPrice = marketProductsEntity.getProductPrice();
        this.location = marketProductsEntity.getLocation();
        this.imageUris = marketItemListEntity.getItemImages().stream()
        .map(ItemImageEntity::getItemImageUri)
        .collect(Collectors.toList());
    }

    public static List<GetAllMarketItemListResponseDto> fromEntityList(List<MarketItemListEntity> entities) {
        return entities.stream()
                .map(entity -> new GetAllMarketItemListResponseDto(entity, entity.getProduct(), entity.getItemImages()))
                .toList();
    }
}
