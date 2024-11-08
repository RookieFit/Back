package com.rookiefit.back.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FindUserIdRequestDto {

    @NotBlank
    private String userPhoneNumber;

}
