package com.rookiefit.back.entity;

import com.rookiefit.back.dto.request.trainer.InputTrainerRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "trainer_license")
public class TrainerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private UserEntity userAuthEntity;

    private String licenseNumber;

    private String licenseImageUrl;

    private String businessRegisterNumber;

    private String businessRegisterImageUrl;


    public TrainerEntity(InputTrainerRequestDto dto, UserEntity userEntity, String licenseImageUrl, String businessRegisterImageUrl) {
        this.licenseNumber = dto.getLicenseNumber();
        this.licenseImageUrl = licenseImageUrl;
        this.businessRegisterNumber = dto.getBusinessRegisterNumber();
        this.businessRegisterImageUrl = businessRegisterImageUrl;
        this.userAuthEntity = userEntity;
    }
}
