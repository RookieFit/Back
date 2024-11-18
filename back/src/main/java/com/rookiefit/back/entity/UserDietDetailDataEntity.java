package com.rookiefit.back.entity;

import com.rookiefit.back.dto.request.UserDietData.InputUserDietDetailRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "user_diet_detail")
public class UserDietDetailDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL에서 AUTO_INCREMENT로 설정
    @Column(name = "id", nullable = false, unique = true) // 'id'가 Primary Key
    private Long id;

    @NotBlank
    @Column(name = "diet_createdDate", nullable = false) // 정확한 필드명
    private UserDietListDataEntity dietCreatedDate;

    @NotBlank
    @Column(name = "food_name", nullable = false)
    private String foodName;

    @NotBlank
    @Column(name = "food_first_category", nullable = false)
    private String foodFirstCategory;

    @Column(name = "chocdf")
    private double chocdf;

    @Column(name = "prot")
    private double prot;

    @Column(name = "fat")
    private double fat;

    @Column(name = "enerc")
    private double enerc;

    public UserDietDetailDataEntity(InputUserDietDetailRequestDto dto) {
        this.foodName = dto.getFood_name();
        this.foodFirstCategory = dto.getFood_first_category();
        this.chocdf = dto.getChocdf();
        this.prot = dto.getProt();
        this.fat = dto.getFat();
        this.enerc = dto.getEnerc();
    }

    public void setUserDietList(UserDietListDataEntity userDietListDataEntity) {
        this.dietCreatedDate = userDietListDataEntity;
    }

}
