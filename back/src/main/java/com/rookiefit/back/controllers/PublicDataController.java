package com.rookiefit.back.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.response.PublicDataResponseDto;
import com.rookiefit.back.service.PublicDataService;

@RestController
@RequestMapping("/api/v1/user/")
public class PublicDataController {

    private final PublicDataService publicDataService;

    public PublicDataController(PublicDataService publicDataService) {
        this.publicDataService = publicDataService;
    }

    @GetMapping("/search")
    public List<PublicDataResponseDto> searchFoods(@RequestParam("keyword") String keyword) {
        return publicDataService.searchFoodByName(keyword);
    }

}
