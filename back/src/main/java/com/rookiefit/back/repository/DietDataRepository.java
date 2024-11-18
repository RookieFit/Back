package com.rookiefit.back.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.DietDataEntity;

@Repository
public interface DietDataRepository extends JpaRepository<DietDataEntity, Long> {
    List<DietDataEntity> findByFoodNameContaining(String foodName);
}
