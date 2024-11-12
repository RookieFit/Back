package com.rookiefit.back.entity;

import org.springframework.stereotype.Component;

import com.google.firebase.database.annotations.NotNull;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDetailRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;

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
@Table(name="user_workout_list_detail")
public class UserWorkoutDetailDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workout_detail_id;

    @NotBlank
    private String workoutDetailCreatedDate;

    @NotBlank
    private String workout_name;

    @NotNull
    private Integer reps;

    @NotNull
    private Integer sets;

    @NotBlank
    private String rest_time;

    public UserWorkoutDetailDataEntity(InputUserWorkoutDetailRequestDto dto) {
        this.workoutDetailCreatedDate = dto.getWorkoutDetailCreatedDate();
        this.workout_name = dto.getWorkoutName();
        this.reps = dto.getReps();
        this.sets = dto.getSets();
        this.rest_time = dto.getRest_time();
    }
}
