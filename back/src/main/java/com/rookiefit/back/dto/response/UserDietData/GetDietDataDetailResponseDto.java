package com.rookiefit.back.dto.response.UserDietData;

import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserDiet.UserDietDetailDataEntity;

import lombok.Getter;

@Getter
public class GetDietDataDetailResponseDto extends ResponseDto {
    private String dietCreatedDate;
    private double totalCalories;
    private List<DietDetail> dietDetails;

    @Getter
    public static class DietDetail {
        private String foodName;
        private String foodFirstCategory;
        private double chocdf;
        private double prot;
        private double fat;
        private double enerc;

        // 생성자 추가
        public DietDetail(String foodName, String foodFirstCategory, double chocdf, double prot, double fat,
                double enerc) {
            this.foodName = foodName;
            this.foodFirstCategory = foodFirstCategory;
            this.chocdf = chocdf;
            this.prot = prot;
            this.fat = fat;
            this.enerc = enerc;
        }
    }

    // 생성자 추가
    public GetDietDataDetailResponseDto(String dietCreatedDate, double totalCalories, List<DietDetail> dietDetails) {
        this.dietCreatedDate = dietCreatedDate;
        this.totalCalories = totalCalories;
        this.dietDetails = dietDetails;
    }

    public static ResponseEntity<List<GetDietDataDetailResponseDto>> success(
            List<UserDietDetailDataEntity> userDietDetailDataEntities, String dietCreatedDate, double totalCalories) {

        // BigDecimal로 소수점 둘째 자리까지 반올림
        BigDecimal totalCaloriesBigDecimal = new BigDecimal(totalCalories).setScale(2, RoundingMode.HALF_UP);
        double roundedTotalCalories = totalCaloriesBigDecimal.doubleValue();

        // UserDietDetailDataEntity를 DietDetail로 변환
        List<DietDetail> dietDetails = userDietDetailDataEntities.stream()
                .map(detail -> new DietDetail(
                        detail.getFoodName(),
                        detail.getFoodFirstCategory(),
                        detail.getChocdf(),
                        detail.getProt(),
                        detail.getFat(),
                        detail.getEnerc()))
                .collect(Collectors.toList());

        // GetDietDataDetailResponseDto 객체 생성
        GetDietDataDetailResponseDto responseDto = new GetDietDataDetailResponseDto(dietCreatedDate,
                roundedTotalCalories,
                dietDetails);

        // 성공적으로 응답 반환
        return ResponseEntity.status(HttpStatus.OK).body(List.of(responseDto));
    }
}
