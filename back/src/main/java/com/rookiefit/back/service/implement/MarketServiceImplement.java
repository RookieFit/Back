package com.rookiefit.back.service.implement;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rookiefit.back.dto.request.Market.MarketItemListRequestDto;
import com.rookiefit.back.dto.request.Market.MarketProductRequestDto;
import com.rookiefit.back.dto.response.Market.DeleteMarketItemResponseDto;
import com.rookiefit.back.dto.response.Market.GetAllMarketItemListResponseDto;
import com.rookiefit.back.dto.response.Market.GetMarketItemResponseDto;
import com.rookiefit.back.dto.response.Market.InputMarketItemListResponseDto;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.entity.Market.ItemImageEntity;
import com.rookiefit.back.entity.Market.MarketItemListEntity;
import com.rookiefit.back.entity.Market.MarketProductsEntity;
import com.rookiefit.back.entity.enums.SaleStatus;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.repository.Market.ItemImageRepository;
import com.rookiefit.back.repository.Market.MarketItemListRepository;
import com.rookiefit.back.service.FirebaseService;
import com.rookiefit.back.service.MarketService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class MarketServiceImplement implements MarketService{

    private final JwtProvider jwtProvider;
    private final MarketItemListRepository marketItemListRepository;
    private final UserProfileRepository userProfileRepository;
    private final ItemImageRepository imageRepository;
    private final FirebaseService firebaseService;

    @Override
    public ResponseEntity<? super InputMarketItemListResponseDto> inputMarketItemList(MarketItemListRequestDto dto) {
        // 현재 사용자 ID를 추출
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());

        // 사용자 프로필 조회
        UserProfileEntity userProfile = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
        if (userProfile == null) {
            return ResponseEntity.badRequest().body("User profile not found");
        }

        // MarketItemListEntity 생성 및 매핑
        MarketItemListEntity marketItemList = new MarketItemListEntity(dto, userProfile);

        // MarketProductsEntity 생성 및 매핑
        MarketProductRequestDto productDto = dto.getProduct();
        MarketProductsEntity product = new MarketProductsEntity(productDto, marketItemList, userProfile);
        marketItemList.setProduct(product);

        // MarketItemListEntity 저장
        marketItemList = marketItemListRepository.save(marketItemList);

        // 이미지 파일 처리 및 저장
        if (dto.getItemImageFiles() != null && dto.getItemImageFiles().length > 0) {
            List<MultipartFile> files = Arrays.asList(dto.getItemImageFiles());

            try {
                // Firebase에 파일 업로드
                List<String> imageUris = firebaseService.uploadFiles(files);
                // 이미지 엔티티 생성 및 마켓 아이템과 연결
                for (String imageUri : imageUris) {
                    ItemImageEntity itemImageEntity = new ItemImageEntity(imageUri);
                    itemImageEntity.setMarketItemList(marketItemList);  // 이미지와 마켓 아이템 연결
                    marketItemList.addItemImage(itemImageEntity);  // 마켓 아이템에 이미지 추가
                }
                // 이미지 엔티티들을 DB에 저장
                marketItemListRepository.save(marketItemList); // 수정된 marketItemList와 그에 포함된 이미지들 저장
            } catch (IOException exception) {
                exception.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Image upload failed");
            }
        }

        return InputMarketItemListResponseDto.success();
    }

    //마켓 게시물 수정하기 이미지처리 구현 완(241204-15:06_김민준)
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
        // 기존 이미지 삭제
        List<ItemImageEntity> existingImages =
            imageRepository.findByMarketItemList_MarketListId(marketListId);// 게시물 id에 해당하는 이미지리스트 찾기
        if(existingImages != null){
            for (ItemImageEntity image : existingImages) { // 이미지리스트가 존재한다면 
                imageRepository.delete(image); // DB에서 삭제
                firebaseService.deleteFile(image.getItemImageUri()); // Firebase에서 삭제
            }
        }
        // 새 이미지 업로드 및 저장
        if (dto.getItemImageFiles() != null && dto.getItemImageFiles().length > 0) {
            List<MultipartFile> fileList = Arrays.asList(dto.getItemImageFiles()); // 이미지 파일들 리스트화
            try {
                List<String> uploadedUrls = firebaseService.uploadFiles(fileList); // Firebase 업로드 후 이미지 주소 반환
                for (String url : uploadedUrls) {// 이미지 DB에 저장
                    ItemImageEntity newImage = new ItemImageEntity(url);
                    newImage.setMarketItemList(marketItemList);
                    imageRepository.save(newImage);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Image upload failed");
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