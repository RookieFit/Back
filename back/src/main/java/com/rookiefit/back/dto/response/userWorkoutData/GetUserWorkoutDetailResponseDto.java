package com.rookiefit.back.dto.response.userWorkoutData;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserWorkoutDetailDataEntity;

import lombok.Getter;

@Getter
public class GetUserWorkoutDetailResponseDto extends ResponseDto{
    private String workoutDetailCreatedDate;
    private String workoutName;
    private Integer reps;
    private Integer sets;
    private String restTime;
    
    private GetUserWorkoutDetailResponseDto(UserWorkoutDetailDataEntity userWorkoutDetailDataEntity) {
        super();
        this.workoutDetailCreatedDate = userWorkoutDetailDataEntity.getWorkoutDetailCreatedDate();
        this.workoutName = userWorkoutDetailDataEntity.getWorkout_name();
        this.reps = userWorkoutDetailDataEntity.getReps();
        this.sets = userWorkoutDetailDataEntity.getSets();
        this.restTime = userWorkoutDetailDataEntity.getRest_time();
    }

    public static ResponseEntity<List<GetUserWorkoutDetailResponseDto>> success(List<UserWorkoutDetailDataEntity> userWorkoutDetailDataEntity) {
        List<GetUserWorkoutDetailResponseDto> userWorkoutDetailDatas = userWorkoutDetailDataEntity.stream()
            .map(GetUserWorkoutDetailResponseDto::new)  // Convert entities to DTOs
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(userWorkoutDetailDatas);
    }
}
