package com.rookiefit.back.entity;

import org.springframework.stereotype.Component;

import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Component
@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile")
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long userProfileId;

    @NotBlank
    private String userId;

    @NotBlank
    private String userProfileImageUri;

    @NotBlank
    private String gymName;

    private String userMessage;

    @NotBlank
    private String userName;

    @NotBlank
    private String userAddress;

    @NotBlank
    private String userNickname;

    public UserProfileEntity(InputUserProfileRequestDto dto) {
        this.userId = dto.getToken(); // 디코딩된 userId
        this.userProfileImageUri = dto.getUserProfileImageUri();
        this.gymName = dto.getGymName();
        this.userMessage = dto.getUserMessage();
        this.userName = dto.getUserName();
        this.userAddress = dto.getUserAddress();
        this.userNickname = dto.getUserNickname();

    }
}