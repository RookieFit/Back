package com.rookiefit.back.repository.Market;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.Market.MarketItemListEntity;
import com.rookiefit.back.entity.enums.SaleStatus;

@Repository
public interface MarketItemListRepository extends JpaRepository<MarketItemListEntity,Long>{
    @Query("SELECT m FROM MarketItemListEntity m JOIN FETCH m.product")
    List<MarketItemListEntity> findAllWithProducts();

    @Query("SELECT m FROM MarketItemListEntity m JOIN FETCH m.product p WHERE p.saleStatus = :saleStatus")
    List<MarketItemListEntity> findAllBySaleStatusWithProducts(@Param("saleStatus") SaleStatus saleStatus);

    @Query("SELECT m FROM MarketItemListEntity m JOIN FETCH m.product WHERE m.id = :marketItemId")
    MarketItemListEntity findByIdWithProduct(@Param("marketItemId") Long marketItemId);

    // 제목에 키워드 포함
    List<MarketItemListEntity> findByMarketItemTitleContaining(String keyword);

    // 내용에 키워드 포함
    List<MarketItemListEntity> findByProduct_ProductDescriptionContaining(String keyword);

    // 지역에 키워드 포함
    List<MarketItemListEntity> findByProduct_LocationContaining(String keyword);

    // 제목, 내용, 지역을 모두 검색
    List<MarketItemListEntity> findByMarketItemTitleContainingOrProduct_ProductDescriptionContainingOrProduct_LocationContaining(
        String titleKeyword, String descriptionKeyword, String locationKeyword);
}
