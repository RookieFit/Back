package com.rookiefit.back.dto.request.userData;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserProfileInputRequestDto {


    @NotBlank
    private String userId;
    
    @NotBlank
    private String userProfileImageUri;

    @NotBlank
    private String gymName;

    private String userMessage;

    @NotBlank
    private String userName;

    @NotBlank
    private String userAddress;
    
    @NotBlank
    private String userNickname;
}
