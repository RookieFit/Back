package com.rookiefit.back.entity;

import org.springframework.stereotype.Component;

import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDataRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Component
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_workout_list")
public class UserWorkoutListDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workoutListId;

    @NotBlank
    private String userId;

    private String comment;

    @NotBlank
    private String workout_title;

    @NotBlank
    private String workoutCreatedDate;

    public UserWorkoutListDataEntity(InputUserWorkoutDataRequestDto dto) {
        this.userId = dto.getToken();//token에서 추출한 userId 저장
        this.comment = dto.getComment();
        this.workout_title = dto.getWorkout_title();
        this.workoutCreatedDate = dto.getWorkoutCreatedData();
    }
}
