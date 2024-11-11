package com.rookiefit.back.dto.response.userData;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserProfileEntity;

import lombok.Getter;

@Getter
public class GetUserProfileResponseDto extends ResponseDto{
    
    private String userProfileImageUri;
    private String gymName;
    private String userMessage;
    private String userName;
    private String userAddress;
    private String userNickname;

    private GetUserProfileResponseDto(UserProfileEntity userProfileEntity) {
        super();
        this.userProfileImageUri = userProfileEntity.getUserProfileImageUri();
        this.gymName = userProfileEntity.getGymName();
        this.userMessage = userProfileEntity.getUserMessage();
        this.userName = userProfileEntity.getUserName();
        this.userAddress = userProfileEntity.getUserAddress();
        this.userNickname = userProfileEntity.getUserNickname();
    }

    public static ResponseEntity<GetUserProfileResponseDto> success(UserProfileEntity userProfileEntity) {
        GetUserProfileResponseDto responseBody = new GetUserProfileResponseDto(userProfileEntity);
    return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
