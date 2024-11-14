package com.rookiefit.back.dto.request.userWorkoutData;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetUserWorkoutListRequestDto {
    @NotBlank
    private String token;
}
