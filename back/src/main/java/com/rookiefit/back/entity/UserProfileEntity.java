package com.rookiefit.back.entity;

import org.springframework.stereotype.Component;

import com.rookiefit.back.dto.request.userData.UserProfileInputRequestDto;

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

    public UserProfileEntity(UserProfileInputRequestDto dto) {
        this.userId = dto.getUserId();
        this.userProfileImageUri = dto.getUserProfileImageUri();
        this.gymName = dto.getGymName();
        this.userMessage = dto.getUserMessage();
        this.userName = dto.getUserName();
        this.userAddress = dto.getUserAddress();
        this.userNickname = dto.getUserNickname();

        System.out.println("entity userid :" + userId);
    
    }
}


