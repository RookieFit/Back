package com.rookiefit.back.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.rookiefit.back.entity.PublicDataEntity;
import com.rookiefit.back.service.PublicDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/")
public class PublicDataController {

    private final PublicDataService publicDataService;

    public PublicDataController(PublicDataService publicDataService) {
        this.publicDataService = publicDataService;
    }

    @GetMapping("/search")
    public List<PublicDataEntity> searchFoods(@RequestParam String keyword) {
        return publicDataService.searchFoodByName(keyword);
    }
}
