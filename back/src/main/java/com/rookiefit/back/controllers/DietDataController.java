package com.rookiefit.back.controllers;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.response.UserDietData.GetDietDataResponseDto;
import com.rookiefit.back.service.DietDataService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class DietDataController {

    private final DietDataService dietDataService;

    @GetMapping("/search")
    public List<GetDietDataResponseDto> searchFoods(@RequestParam("keyword") String keyword) {
        return dietDataService.searchFoodByName(keyword);
    }

}
