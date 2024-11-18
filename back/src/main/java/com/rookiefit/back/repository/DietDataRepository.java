package com.rookiefit.back.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rookiefit.back.entity.DietDataEntity;

public interface DietDataRepository extends JpaRepository<DietDataEntity, Long> {
    List<DietDataEntity> findByFoodNameContaining(String foodName);
}
