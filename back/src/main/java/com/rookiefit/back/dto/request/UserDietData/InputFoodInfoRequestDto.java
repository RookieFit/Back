package com.rookiefit.back.dto.request.UserDietData;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputFoodInfoRequestDto {

    @NotBlank
    private String food_name;

    private Double chocdf;

    private Double prot;

    private Double fatce;

    private Double enerc;
}
