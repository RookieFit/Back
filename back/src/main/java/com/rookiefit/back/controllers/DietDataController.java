package com.rookiefit.back.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.response.UserDietData.GetDietDataResponseDto;
import com.rookiefit.back.service.DietDataService;

@RestController
@RequestMapping("/api/v1/user/")
public class DietDataController {

    private final DietDataService dietDataService;

    public DietDataController(DietDataService dietDataService) {
        this.dietDataService = dietDataService;
    }

    @GetMapping("/dietsearch")
    public List<GetDietDataResponseDto> searchFoods(@RequestParam("keyword") String keyword) {
        return dietDataService.searchFoodByName(keyword);
    }

}
