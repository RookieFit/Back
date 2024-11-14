package com.rookiefit.back.dto.response.userWorkoutData;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserWorkoutDetailDataEntity;

import lombok.Getter;

@Getter
public class GetUserWorkoutDetailResponseDto extends ResponseDto{
    private List<UserWorkoutDetailDataEntity> userWorkoutDetailData;
    private GetUserWorkoutDetailResponseDto(List<UserWorkoutDetailDataEntity> userWorkoutDetailDataEntity) {
        super();
        this.userWorkoutDetailData = userWorkoutDetailDataEntity;
    }

    public static ResponseEntity<GetUserWorkoutDetailResponseDto> success(List<UserWorkoutDetailDataEntity> userWorkoutDetailDataEntity) {
        GetUserWorkoutDetailResponseDto responseBody = new GetUserWorkoutDetailResponseDto(userWorkoutDetailDataEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
