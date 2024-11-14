package com.rookiefit.back.entity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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

    @OneToMany(mappedBy = "userWorkoutList", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<UserWorkoutDetailDataEntity> workoutDetails;

    public UserWorkoutListDataEntity(InputUserWorkoutListRequestDto dto) {
        this.userId = dto.getToken();  // Extracted userId from token
        this.comment = dto.getComment();
        this.workoutTitle = dto.getWorkout_title();
        this.workoutCreatedDate = dto.getWorkoutCreatedData();
    }
}