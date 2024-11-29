package com.rookiefit.back.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.Market.MarketItemListRequestDto;
import com.rookiefit.back.dto.request.Market.MarketProductRequestDto;
import com.rookiefit.back.dto.response.Market.DeleteMarketItemResponseDto;
import com.rookiefit.back.dto.response.Market.GetAllMarketItemListResponseDto;
import com.rookiefit.back.dto.response.Market.GetMarketItemResponseDto;
import com.rookiefit.back.dto.response.Market.InputMarketItemListResponseDto;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.entity.Market.MarketItemListEntity;
import com.rookiefit.back.entity.Market.MarketProductsEntity;
import com.rookiefit.back.entity.enums.SaleStatus;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.repository.Market.MarketItemListRepository;
import com.rookiefit.back.service.MarketService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MarketServiceImplement implements MarketService{

    private final JwtProvider jwtProvider;
    private final MarketItemListRepository marketItemListRepository;
    private final UserProfileRepository userProfileRepository;

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
        MarketProductsEntity product = new MarketProductsEntity(productDto, marketItemList, userProfile);
        marketItemList.setProduct(product);
        marketItemListRepository.save(marketItemList);
        
        return InputMarketItemListResponseDto.success();
    }

    @Override
    public ResponseEntity<? super InputMarketItemListResponseDto> updateMarketItemList(MarketItemListRequestDto dto, Long marketListId) {
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
        // 1. MarketItemListEntity 조회
        Optional<MarketItemListEntity> optionalMarketItemList = marketItemListRepository.findById(marketListId);
        if (optionalMarketItemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Market item not found with ID: " + marketListId);
        }
        MarketItemListEntity marketItemList = optionalMarketItemList.get();

        // 2. Entity의 update 메서드 호출
        marketItemList.update(dto);

        // 3. Product 업데이트 (필요 시 별도 로직)
        MarketProductRequestDto productDto = dto.getProduct();
        UserProfileEntity userProfile = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
        if (userProfile == null) {
            return ResponseEntity.badRequest().body("User profile not found");
        }
        if (productDto != null) {
            MarketProductsEntity product = marketItemList.getProduct();
            if (product != null) {
                product.update(productDto,userProfile); // Product의 업데이트 메서드 호출
            }
        }
        
        // 4. 엔티티 저장
        marketItemListRepository.save(marketItemList);
        return InputMarketItemListResponseDto.success();
    }

    @Override
    public ResponseEntity<List<GetAllMarketItemListResponseDto>> getAllMarketItemList() {
        // 모든 MarketItemListEntity와 관련된 MarketProductsEntity를 함께 조회
        List<MarketItemListEntity> marketItemList = marketItemListRepository.findAllWithProducts();

        if (marketItemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        // DTO 변환
        List<GetAllMarketItemListResponseDto> responseList = GetAllMarketItemListResponseDto.fromEntityList(marketItemList);
        // 응답 반환
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<List<GetAllMarketItemListResponseDto>> getBySaleStatusMarketList(String saleStatus) {
        // SaleStatus 값을 Enum으로 변환
        SaleStatus status = SaleStatus.valueOf(saleStatus.toUpperCase());
        // 해당 상태에 맞는 MarketItemListEntity 조회
        List<MarketItemListEntity> marketItemList = marketItemListRepository.findAllBySaleStatusWithProducts(status);
        if (marketItemList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        // DTO 변환
        List<GetAllMarketItemListResponseDto> responseList = GetAllMarketItemListResponseDto.fromEntityList(marketItemList);
        // 응답 반환
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<? super GetMarketItemResponseDto> getMarketItem(Long id) {
        // 해당 게시물 조회
        MarketItemListEntity marketItem = marketItemListRepository.findByIdWithProduct(id);
        if (marketItem == null) {
            // 게시물이 없는 경우 404 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // DTO 변환
        GetMarketItemResponseDto response = new GetMarketItemResponseDto(marketItem);
        return ResponseEntity.ok(response);
    }
    
    @Override
    public ResponseEntity<List<GetAllMarketItemListResponseDto>> getByKeywordMarketList(String keyword, String field) {
        List<MarketItemListEntity> marketItems;
        if (field == null || field.isEmpty()) {
            // field가 없는 경우: 제목, 내용, 지역 전체 검색
            marketItems = marketItemListRepository
                .findByMarketItemTitleContainingOrProduct_ProductDescriptionContainingOrProduct_LocationContaining(
                    keyword, keyword, keyword);
        } else if ("title".equalsIgnoreCase(field)) {
            // 제목 검색
            marketItems = marketItemListRepository.findByMarketItemTitleContaining(keyword);
        } else if ("description".equalsIgnoreCase(field)) {
            // 내용 검색
            marketItems = marketItemListRepository.findByProduct_ProductDescriptionContaining(keyword);
        } else if ("location".equalsIgnoreCase(field)) {
            // 지역 검색
            marketItems = marketItemListRepository.findByProduct_LocationContaining(keyword);
        } else {
            // field 값이 잘못된 경우 예외 처리
            return ResponseEntity.badRequest().build();
        }
        if (marketItems.isEmpty()) {
            // 검색 결과 없음
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        // DTO 변환
        List<GetAllMarketItemListResponseDto> responseList = GetAllMarketItemListResponseDto.fromEntityList(marketItems);
        // 응답 반환
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<? super DeleteMarketItemResponseDto> deleteMarketItem(Long id) {
        if (!marketItemListRepository.existsById(id)) {
            return DeleteMarketItemResponseDto.marketListNotFound();
        }
        marketItemListRepository.deleteById(id);
        return DeleteMarketItemResponseDto.success();
    }
}