package com.rookiefit.back.controllers;

import com.rookiefit.back.dto.request.UserDietData.InputFoodInfoRequestDto;
import com.rookiefit.back.dto.request.UserDietData.InputUserDietListRequestDto;
import com.rookiefit.back.dto.response.UserDietData.DeleteUserDietListResponseDto;
import com.rookiefit.back.dto.response.UserDietData.GetDietDataDetailResponseDto;
import com.rookiefit.back.dto.response.UserDietData.GetDietDataResponseDto;
import com.rookiefit.back.dto.response.UserDietData.InputFoodInfoResponseDto;
import com.rookiefit.back.dto.response.UserDietData.InputUserDietListResponseDto;
import com.rookiefit.back.service.FoodInfoService;
import com.rookiefit.back.service.UserDietDataService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserDietDataController {

    private final UserDietDataService userDietDataService;
    private final FoodInfoService foodInfoService;

    @PostMapping("/input-userdietlistdata")
    public ResponseEntity<? super InputUserDietListResponseDto> inputUserDietData(
            @RequestBody @Valid InputUserDietListRequestDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String)authentication.getPrincipal();
        ResponseEntity<? super InputUserDietListResponseDto> responseBody = userDietDataService.inputUserDietData(dto, currentUserId);
        return responseBody;
    }

    //241205-10:01_(기능구현자 == {김경은})/Feat.김민준 : @RequestParam으로 교체
    @DeleteMapping("/delete-userdietlistdata")
    public ResponseEntity<? super DeleteUserDietListResponseDto> deleteUserDietData(
            @PathVariable Long userDietDetailId) {
        ResponseEntity<? super DeleteUserDietListResponseDto> responseBody = userDietDataService
                .deleteUserDietData(userDietDetailId);
        return responseBody;
    }

    //241205-09:43_(기능구현자 == {김경은})/Feat.김민준 : @RequestParam으로 교체
    @GetMapping("/userdietlistdata")
    public ResponseEntity<List<GetDietDataDetailResponseDto>> getUserDietListData(
            @RequestParam("diet_created_date") String diet_created_date) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = (String)authentication.getPrincipal();
        ResponseEntity<List<GetDietDataDetailResponseDto>> responseBody = userDietDataService
                .getUserDietListData(diet_created_date,currentUserId);
        return responseBody;
    }

    // 식단 검색
    @GetMapping("/dietsearch")
    public List<GetDietDataResponseDto> searchFoods(@RequestParam("keyword") String keyword) {
        return foodInfoService.searchFoodByName(keyword);
    }

    // 식단 insert
    @PostMapping("/insert-foodinfo")
    public ResponseEntity<? super InputFoodInfoResponseDto> insertFoodInfo(@RequestBody InputFoodInfoRequestDto dto) {
        return foodInfoService.insertFoodInfo(dto);
    }
}