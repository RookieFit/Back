package com.rookiefit.back.dto.response.UserDietData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.entity.UserDiet.FoodInfoEntity;

import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class GetDietDataResponseDto {
    private Long id;
    private String foodName;
    private String foodFirstCategory;
    private double chocdf;
    private double prot;
    private double fatce;
    private double enerc;

    private GetDietDataResponseDto(FoodInfoEntity entity) {
        this.id = entity.getId();
        this.foodName = entity.getFoodName();
        this.foodFirstCategory = entity.getFoodFirstCategory();
        this.chocdf = entity.getChocdf();
        this.prot = entity.getProt();
        this.fatce = entity.getFatce();
        this.enerc = entity.getEnerc();
    }

    // 개별 엔티티를 받아서 대응되는 DTO로 변환하는 메서드
    public static GetDietDataResponseDto fromEntity(FoodInfoEntity entity) {
        return new GetDietDataResponseDto(entity);
    }

    // 엔티티 리스트를 받아서 각각을 DTO로 변환한 후 리스트로 반환하는 메서드
    public static List<GetDietDataResponseDto> fromEntityList(List<FoodInfoEntity> entities) {
        return entities.stream().map(GetDietDataResponseDto::fromEntity).collect(Collectors.toList());
    }

    // 단일 엔티티가 성공적으로 처리된 후, 해당 엔티티를 DTO로 변환하여 성공 응답을 생성하는 메서드
    public static ResponseEntity<GetDietDataResponseDto> success(FoodInfoEntity entity) {
        return ResponseEntity.status(HttpStatus.OK).body(new GetDietDataResponseDto(entity));
    }

    // 여러 엔티티가 성공적으로 처리된 후, 이들을 DTO 리스트로 변환하여 성공 응답을 생성하는 메서드
    public static ResponseEntity<List<GetDietDataResponseDto>> success(List<FoodInfoEntity> entities) {
        return ResponseEntity.status(HttpStatus.OK).body(fromEntityList(entities));
    }
}
