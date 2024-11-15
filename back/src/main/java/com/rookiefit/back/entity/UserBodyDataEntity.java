package com.rookiefit.back.entity;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.rookiefit.back.common.CurrentDate;
import com.rookiefit.back.dto.request.userData.InputUserBodyDataRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_body_information")
public class UserBodyDataEntity {

    @NotNull
    private Integer user_age;

    @NotNull
    private Integer user_weight;

    @NotNull
    private Integer user_height;

    @NotNull
    private Integer user_muscle_mass;

    @NotNull
    private Integer user_fat_mass;

    @Id
    @NotBlank
    private String inbodydate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private UserProfileEntity userProfile;

    public UserBodyDataEntity(InputUserBodyDataRequestDto dto) {
        this.user_age = dto.getUserAge();
        this.user_weight = dto.getUserWeight();
        this.user_height = dto.getUserHeight();
        this.user_muscle_mass = dto.getUserMuscleMass();
        this.user_fat_mass = dto.getUserFatMass();
        this.inbodydate = dto.getInbodydate();
    }

    public void setUserProfileData(UserProfileEntity userProfileEntity) {
        this.userProfile = userProfileEntity;
    }
}
