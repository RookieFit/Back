package com.rookiefit.back.entity;

import com.rookiefit.back.dto.request.UserDietData.InputUserDietListRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user_diet")
public class UserDietListDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // MySQL에서 AUTO_INCREMENT로 설정
    @Column(name = "userdietid", nullable = false, unique = true) // 'userdietid'가 Primary Key
    private Long userdietid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id") // 'user_id'는 외래 키
    private UserProfileEntity userProfile;

    @NotBlank
    private String dietCreatedDate;

    @Column(name = "total_calories")
    private double totalCalories;

    public UserDietListDataEntity(InputUserDietListRequestDto dto, UserProfileEntity userProfile) {
        this.dietCreatedDate = dto.getDiet_create_date();
        dto.calculateTotalCalories(); // 총 칼로리 계산
        this.totalCalories = dto.getTotal_calories(); // 계산된 칼로리 값 설정
        this.userProfile = userProfile;
    }
}
