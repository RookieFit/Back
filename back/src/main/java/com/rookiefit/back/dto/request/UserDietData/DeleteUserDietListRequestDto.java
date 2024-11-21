package com.rookiefit.back.dto.request.UserDietData;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteUserDietListRequestDto {

    @NotBlank
    private String token;

    @NotBlank
    private String diet_created_date;

}
