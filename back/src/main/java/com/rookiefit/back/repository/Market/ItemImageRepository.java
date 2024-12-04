package com.rookiefit.back.repository.Market;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.Market.ItemImageEntity;

@Repository
public interface ItemImageRepository extends JpaRepository<ItemImageEntity, Long>{
    List<ItemImageEntity> findByMarketItemList_MarketListId(Long marketListId);
}
