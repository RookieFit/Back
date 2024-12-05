package com.rookiefit.back.entity.UserWorkout;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDetailRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.entity.UserProfileEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_workout_list")
@IdClass(UserWorkoutListDataId.class)
public class UserWorkoutListDataEntity {

    @Id
    @Column(name = "workout_created_date")
    //@Pattern(regexp = "^\\d{2}-\\d{2}-\\d{2}$", message = "날짜 형식은 'yy-MM-dd'이어야 합니다.")
    private String workoutCreatedDate;

    @Id
    @Column(name = "user_id")
    private String userId;

    private String comment;

    @NotBlank
    private String workoutTitle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , referencedColumnName = "user_id", insertable = false, updatable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserProfileEntity userProfile;

    @OneToMany(mappedBy = "userWorkoutList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<UserWorkoutDetailDataEntity> workoutDetails = new ArrayList<>();

    @OneToMany(mappedBy = "userWorkoutList", cascade = CascadeType.ALL)
    private List<UserWorkoutImagesEntity> userWorkoutImages = new ArrayList<>();

    public UserWorkoutListDataEntity(InputUserWorkoutListRequestDto dto, String currentUserId, UserProfileEntity userProfileEntity) {
        this.userId = currentUserId;
        this.comment = dto.getComment();
        this.workoutTitle = dto.getWorkout_title();
        this.workoutCreatedDate = dto.getWorkoutCreatedData();
        this.userProfile = userProfileEntity;
    }

    public void updateWorkoutData(InputUserWorkoutListRequestDto dto) {
        this.comment = dto.getComment();
        this.workoutTitle = dto.getWorkout_title();
    }

    public void addWorkoutDetails(List<InputUserWorkoutDetailRequestDto> workoutDetails) {
        for (InputUserWorkoutDetailRequestDto workoutDetailDto : workoutDetails) {
            UserWorkoutDetailDataEntity detail = new UserWorkoutDetailDataEntity(workoutDetailDto);
            detail.setUserWorkoutList(this);  // 외래 키 설정
            this.workoutDetails.add(detail);
        }
    }

    public void addWorkoutImages(List<String> imageUris) {
        for (String imageUri : imageUris) {
            UserWorkoutImagesEntity imageEntity = new UserWorkoutImagesEntity(imageUri);
            imageEntity.setUserWorkoutList(this);
            this.userWorkoutImages.add(imageEntity);
        }
    }
}