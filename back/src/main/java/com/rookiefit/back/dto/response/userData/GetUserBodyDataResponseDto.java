package com.rookiefit.back.dto.response.userData;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserBodyDataEntity;

import lombok.Getter;

@Getter
public class GetUserBodyDataResponseDto extends ResponseDto{

    private List<UserBodyDataEntity> userBodyData;

    GetUserBodyDataResponseDto(List<UserBodyDataEntity> userBodyDataEntity ) {
        super();
        this.userBodyData = userBodyDataEntity;
    }

    public static ResponseEntity<GetUserBodyDataResponseDto> success(List<UserBodyDataEntity> userBodyDataEntity) {
        GetUserBodyDataResponseDto responseBody = new GetUserBodyDataResponseDto(userBodyDataEntity);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);

    }
}
