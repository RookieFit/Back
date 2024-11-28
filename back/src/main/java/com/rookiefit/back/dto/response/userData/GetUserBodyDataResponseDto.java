package com.rookiefit.back.dto.response.userData;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserBodyDataEntity;

import lombok.Getter;

@Getter
public class GetUserBodyDataResponseDto extends ResponseDto {

    private Integer userAge;
    private Integer userWeight;
    private Integer userHeight;
    private Integer userMuscleMass;
    private Integer userFatMass;
    private String inbodyDate;

    public GetUserBodyDataResponseDto(UserBodyDataEntity entity) {
        this.userAge = entity.getUser_age();
        this.userWeight = entity.getUser_weight();
        this.userHeight = entity.getUser_height();
        this.userMuscleMass = entity.getUser_muscle_mass();
        this.userFatMass = entity.getUser_fat_mass();
        this.inbodyDate = entity.getInbodydate();
    }

    public static ResponseEntity<List<GetUserBodyDataResponseDto>> success(
            List<UserBodyDataEntity> userBodyDataEntity) {
        List<GetUserBodyDataResponseDto> responseBody = userBodyDataEntity.stream()
                .map(GetUserBodyDataResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
