package com.rookiefit.back.dto.request.Market;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MarketItemListRequestDto {

    @NotBlank
    private String token;

    private boolean isSold;

    private Long marketListId;

    @NotBlank
    private String marketItemTitle;

    private String marketItemImageUrl;

    @NotNull
    private Date createdAt;

    private Date updatedAt;

    private MarketProductRequestDto product;
}
