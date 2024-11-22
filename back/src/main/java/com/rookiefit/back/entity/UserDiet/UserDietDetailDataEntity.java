package com.rookiefit.back.entity.UserDiet;

import com.rookiefit.back.dto.request.UserDietData.InputUserDietDetailRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_diet_detail")
public class UserDietDetailDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_diet_detail_id", nullable = false, unique = true)
    private Long userDietDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "diet_created_date", nullable = false)
    private UserDietListDataEntity userDietListData;

    @Column(name = "food_name", nullable = false)
    private String foodName;

    @Column(name = "chocdf")
    private double chocdf;

    @Column(name = "prot")
    private double prot;

    @Column(name = "fat")
    private double fat;

    @Column(name = "enerc")
    private double enerc;

    @Column(name = "food_first_category", nullable = false)
    private String foodFirstCategory;

    public UserDietDetailDataEntity(InputUserDietDetailRequestDto dto, UserDietListDataEntity userDietListDataEntity) {
        this.foodName = dto.getFood_name();
        this.foodFirstCategory = dto.getFood_first_category();
        this.chocdf = dto.getChocdf();
        this.prot = dto.getProt();
        this.fat = dto.getFat();
        this.enerc = dto.getEnerc();
        this.userDietListData = userDietListDataEntity;
    }
}
