package com.rookiefit.back.dto.request.UserDietData;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GetDietDataDetailRequestDto {

    @NotBlank
    private String token;

    @NotBlank
    private String diet_created_date;
}
