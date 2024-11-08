package com.rookiefit.back.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CheckFindUserIdRequestDto {

    @NotBlank
    private String userPhoneNumber;

    @NotBlank
    private String certificationNumber;

}
