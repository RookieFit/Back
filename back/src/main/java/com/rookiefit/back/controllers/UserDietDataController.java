package com.rookiefit.back.controllers;

import com.rookiefit.back.dto.request.UserDietData.DeleteUserDietListRequestDto;
import com.rookiefit.back.dto.request.UserDietData.GetDietDataDetailRequestDto;
import com.rookiefit.back.dto.request.UserDietData.InputUserDietListRequestDto;
import com.rookiefit.back.dto.response.UserDietData.DeleteUserDietListResponseDto;
import com.rookiefit.back.dto.response.UserDietData.GetDietDataDetailResponseDto;
import com.rookiefit.back.dto.response.UserDietData.GetDietDataResponseDto;
import com.rookiefit.back.dto.response.UserDietData.InputUserDietListResponseDto;
import com.rookiefit.back.service.DietDataService;
import com.rookiefit.back.service.UserDietDataService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user/")
@RequiredArgsConstructor
public class UserDietDataController {

    private final UserDietDataService userDietDataService;
    private final DietDataService dietDataService;

    @PostMapping("/input-userdietlistdata")
    public ResponseEntity<? super InputUserDietListResponseDto> inputUserDietData(
            @RequestBody @Valid InputUserDietListRequestDto dto) {
        ResponseEntity<? super InputUserDietListResponseDto> responseBody = userDietDataService.inputUserDietData(dto);
        return responseBody;
    }

    @DeleteMapping("/delete-userdietlistdata")
    public ResponseEntity<? super DeleteUserDietListResponseDto> deleteUserDietData(
            @RequestBody @Valid DeleteUserDietListRequestDto dto) {
        ResponseEntity<? super DeleteUserDietListResponseDto> responseBody = userDietDataService
                .deleteUserDietData(dto);
        return responseBody;
    }

    @GetMapping("/userdietlistdata")
    public ResponseEntity<List<GetDietDataDetailResponseDto>> getUserDietListData(
            @RequestBody @Valid GetDietDataDetailRequestDto dto) {
        ResponseEntity<List<GetDietDataDetailResponseDto>> responseBody = userDietDataService
                .getUserDietListData(dto);
        return responseBody;
    }

    // 식단 검색
    @GetMapping("/dietsearch")
    public List<GetDietDataResponseDto> searchFoods(@RequestParam("keyword") String keyword) {
        return dietDataService.searchFoodByName(keyword);
    }
}