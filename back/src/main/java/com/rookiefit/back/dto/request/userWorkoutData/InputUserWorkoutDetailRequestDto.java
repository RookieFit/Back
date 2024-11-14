package com.rookiefit.back.dto.request.userWorkoutData;

import com.google.firebase.database.annotations.NotNull;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputUserWorkoutDetailRequestDto {

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

}
