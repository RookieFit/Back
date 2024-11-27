package com.rookiefit.back.repository.Market;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.Market.MarketProductsEntity;

@Repository
public interface MarketProductsRepository extends JpaRepository<MarketProductsEntity,Long> {
    
}
