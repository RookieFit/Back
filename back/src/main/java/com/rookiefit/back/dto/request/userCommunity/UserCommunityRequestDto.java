package com.rookiefit.back.dto.request.userCommunity;

import java.time.LocalDateTime;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCommunityRequestDto {

    @NotBlank
    private String token;

    private Long communityListId;

    @NotBlank
    private String communityTitle;

    @NotBlank
    private String communityContent;
    
    private LocalDateTime createdDate;

    private Boolean isModified;

    private String communityImageUrl;
    
    @NotBlank
    private String communityContentType;

    private MultipartFile[] commnunityImages;
}
