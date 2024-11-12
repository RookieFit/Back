package com.rookiefit.back.dto.request.userData;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputUserBodyDataRequestDto {
    
    @NotBlank
    private String token;

    @NotNull
    private Integer userAge;

    @NotNull
    private Integer userWeight;

    @NotNull
    private Integer userHeight;

    @NotNull
    private Integer userMuscleMass;

    @NotNull
    private Integer userFatMass;
    
}
