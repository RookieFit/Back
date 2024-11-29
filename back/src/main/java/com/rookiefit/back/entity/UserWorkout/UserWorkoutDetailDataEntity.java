package com.rookiefit.back.entity.UserWorkout;

import org.springframework.stereotype.Component;

import com.google.firebase.database.annotations.NotNull;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDetailRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
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
@Table(name="user_workout_list_detail")
public class UserWorkoutDetailDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workout_detail_id;

    @NotBlank
    //@Pattern(regexp = "^\\d{2}-\\d{2}-\\d{2}$", message = "날짜 형식은 'yy-MM-dd'이어야 합니다.")
    private String workoutDetailCreatedDate;

    @NotBlank
    private String workout_name;

    @NotNull
    private Integer reps;

    @NotNull
    private Integer sets;

    @NotBlank
    private String rest_time;

    @ManyToOne
   @JoinColumns({
        @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
        @JoinColumn(name = "workout_created_date", referencedColumnName = "workout_created_date")
    })
    private UserWorkoutListDataEntity userWorkoutList;

    public UserWorkoutDetailDataEntity(InputUserWorkoutDetailRequestDto dto) {
        this.workoutDetailCreatedDate = dto.getWorkoutDetailCreatedDate();
        this.workout_name = dto.getWorkout_name();
        this.reps = dto.getReps();
        this.sets = dto.getSets();
        this.rest_time = dto.getRest_time();
    }
}
