package com.rookiefit.back.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rookiefit.back.entity.PublicDataEntity;
import com.rookiefit.back.repository.PublicDataRepository;

@Service
public class PublicDataService {
    private final PublicDataRepository publicDataRepository;

    @Autowired
    public PublicDataService(PublicDataRepository publicDataRepository) {
        this.publicDataRepository = publicDataRepository;
    }

    public List<PublicDataEntity> searchFoodByName(String keyword) {
        return publicDataRepository.findByFoodNameContaining(keyword);
    }
}
