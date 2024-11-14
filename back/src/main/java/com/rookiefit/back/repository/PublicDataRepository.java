package com.rookiefit.back.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.rookiefit.back.entity.PublicDataEntity;

public interface PublicDataRepository extends JpaRepository<PublicDataEntity, Long> {
    List<PublicDataEntity> findByFoodNameContaining(String foodName);
}
