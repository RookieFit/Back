package com.rookiefit.back.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.entity.UserDiet.UserDietListDataEntity;
import com.rookiefit.back.entity.UserWorkout.UserWorkoutListDataEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile")
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long userProfileId;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    private UserEntity userAuthEntity;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserBodyDataEntity> userBodyDatas;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserDietListDataEntity> userDietLists;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserWorkoutListDataEntity> userWorkoutLists;

    public UserProfileEntity(InputUserProfileRequestDto dto) {

        this.userAuthEntity = new UserEntity(); // userAuthEntity 초기화
        this.userAuthEntity.setUserId(dto.getToken()); // userId 설정
        this.userProfileImageUri = dto.getUserProfileImageUri();
        this.gymName = dto.getGymName();
        this.userMessage = dto.getUserMessage();
        this.userName = dto.getUserName();
        this.userAddress = dto.getUserAddress();
        this.userNickname = dto.getUserNickname();

    }

    public void setUser(UserEntity userEntity) {
        this.userAuthEntity = userEntity;

    }
}