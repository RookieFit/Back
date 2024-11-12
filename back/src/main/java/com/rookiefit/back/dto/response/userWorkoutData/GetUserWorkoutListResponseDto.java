package com.rookiefit.back.dto.response.userWorkoutData;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserWorkoutListDataEntity;

import lombok.Getter;

@Getter
public class GetUserWorkoutListResponseDto extends ResponseDto{
    
    private List<UserWorkoutListDataEntity> userWorkoutListData;

    GetUserWorkoutListResponseDto(List<UserWorkoutListDataEntity> userWorkoutListDataEntity) {
        super();
        this.userWorkoutListData = userWorkoutListDataEntity;
        
    }

    public static ResponseEntity<GetUserWorkoutListResponseDto> success(List<UserWorkoutListDataEntity> userWorkoutListDataEntity) {
        GetUserWorkoutListResponseDto responseBody = new GetUserWorkoutListResponseDto(userWorkoutListDataEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
