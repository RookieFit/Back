package com.rookiefit.back.entity.UserDiet;

import com.rookiefit.back.dto.request.UserDietData.InputFoodInfoRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foodinfo")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "chocdf")
    private Double chocdf;

    @Column(name = "prot")
    private Double prot;

    @Column(name = "fatce")
    private Double fatce;

    @Column(name = "enerc")
    private Double enerc;

    @Column(name = "food_first_category", nullable = false)
    private String foodFirstCategory;

    public FoodInfoEntity(InputFoodInfoRequestDto dto) {
        this.foodName = dto.getFood_name();
        this.foodFirstCategory = "사용자 지정 카테고리";
        this.chocdf = dto.getChocdf();
        this.prot = dto.getProt();
        this.fatce = dto.getFatce();
        this.enerc = dto.getEnerc();
    }
}
