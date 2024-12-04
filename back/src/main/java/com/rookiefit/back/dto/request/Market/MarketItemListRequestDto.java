package com.rookiefit.back.dto.request.Market;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

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

    private String createdAt; // Date 타입에서 String 타입으로 변경

    private String updatedAt; // Date 타입에서 String 타입으로 변경

    private MultipartFile[] itemImageFiles;

    private MarketProductRequestDto product;
}
