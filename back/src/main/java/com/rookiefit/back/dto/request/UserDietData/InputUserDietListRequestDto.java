package com.rookiefit.back.dto.request.UserDietData;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputUserDietListRequestDto {

    @NotBlank
    private String token;

    @NotBlank
    private String diet_create_date;

    private double total_calories; // 총 칼로리

    private String userDietData;

    private List<InputUserDietDetailRequestDto> dietDetails; // 세부 식단 리스트

    // total_calories 값을 dietDetails에서 계산하는 메서드 추가
    public void calculateTotalCalories() {
        this.total_calories = dietDetails.stream()
                .mapToDouble(InputUserDietDetailRequestDto::getEnerc) // 각 음식의 칼로리 합산
                .sum();
    }

}
