package com.rookiefit.back.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FindUserPasswordRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String user_phonenumber;

}
