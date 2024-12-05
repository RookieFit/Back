package com.rookiefit.back.dto.request.userData;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputUserProfileRequestDto {
    
    @NotBlank
    private MultipartFile userProfileImageFile;

    private String gymName;

    private String userMessage;

    private String userName;

    private String userAddress;
    
    private String userNickname;
}
