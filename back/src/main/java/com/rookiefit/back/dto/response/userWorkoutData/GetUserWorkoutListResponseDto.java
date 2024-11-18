package com.rookiefit.back.dto.response.userWorkoutData;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserWorkout.UserWorkoutListDataEntity;

import lombok.Getter;

@Getter
public class GetUserWorkoutListResponseDto extends ResponseDto{
    
    private String comment;
    private String workout_title;
    private String workoutCreatedData;

    GetUserWorkoutListResponseDto(UserWorkoutListDataEntity userWorkoutListDataEntity) {
        this.comment = userWorkoutListDataEntity.getComment();
        this.workout_title = userWorkoutListDataEntity.getWorkoutTitle();
        this.workoutCreatedData = userWorkoutListDataEntity.getWorkoutCreatedDate();
    }
 
    public static ResponseEntity<List<GetUserWorkoutListResponseDto>> success(List<UserWorkoutListDataEntity> userWorkoutListDataEntities) {
        List<GetUserWorkoutListResponseDto> responseBody = userWorkoutListDataEntities.stream()
            .map(GetUserWorkoutListResponseDto::new) // 각 엔티티를 DTO로 변환
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
