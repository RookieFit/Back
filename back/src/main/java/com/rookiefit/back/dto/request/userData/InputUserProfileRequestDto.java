package com.rookiefit.back.dto.request.userData;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputUserProfileRequestDto {
    
    private MultipartFile userProfileImageFile;

    private String gymName;

    private String userMessage;

    private String userName;

    private String userAddress;
    
    private String userNickname = "닉네임 없는 헬린이";
}
