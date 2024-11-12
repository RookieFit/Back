package com.rookiefit.back.dto.response.userWorkoutData;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserWorkoutListDataEntity;

import lombok.Getter;

@Getter
public class GetUserWorkoutDataResponseDto extends ResponseDto{
    
    private List<UserWorkoutListDataEntity> userWorkoutListData;

    GetUserWorkoutDataResponseDto(List<UserWorkoutListDataEntity> userWorkoutListDataEntity) {
        super();
        this.userWorkoutListData = userWorkoutListDataEntity;
        
    }

    public static ResponseEntity<GetUserWorkoutDataResponseDto> success(List<UserWorkoutListDataEntity> userWorkoutListDataEntity) {
        GetUserWorkoutDataResponseDto responseBody = new GetUserWorkoutDataResponseDto(userWorkoutListDataEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
