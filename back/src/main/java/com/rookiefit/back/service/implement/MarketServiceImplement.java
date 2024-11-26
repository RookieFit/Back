package com.rookiefit.back.service.implement;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.Market.MarketItemListRequestDto;
import com.rookiefit.back.dto.request.Market.MarketProductRequestDto;
import com.rookiefit.back.dto.response.Market.InputMarketItemListResponseDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityResponseDto;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.entity.Market.MarketItemListEntity;
import com.rookiefit.back.entity.Market.MarketProductsEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.repository.Market.CategoryRepository;
import com.rookiefit.back.repository.Market.MarketItemListRepository;
import com.rookiefit.back.service.MarketService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MarketServiceImplement implements MarketService{

    private final JwtProvider jwtProvider;
    private final MarketItemListRepository marketItemListRepository;
    private final UserProfileRepository userProfileRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ResponseEntity<? super InputMarketItemListResponseDto> inputMarketItemList(MarketItemListRequestDto dto) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        UserProfileEntity userProfile = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
        if (userProfile == null) {
            return ResponseEntity.badRequest().body("User profile not found");
        }
        MarketItemListEntity marketItemList = new MarketItemListEntity(dto, userProfile);

        // MarketProductsEntity 생성 및 매핑
        MarketProductRequestDto productDto = dto.getProduct();
        MarketProductsEntity product = new MarketProductsEntity(productDto, marketItemList, currentUserId, userProfile);
        marketItemList.setProduct(product);
        marketItemListRepository.save(marketItemList);
        
        return InputMarketItemListResponseDto.success();
    }
}
