package com.rookiefit.back.dto.request.userData;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserProfileRequestDto {
    
    @NotBlank
    private String userId;
}
