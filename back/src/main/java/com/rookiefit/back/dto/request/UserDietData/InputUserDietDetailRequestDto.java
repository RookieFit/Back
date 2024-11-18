package com.rookiefit.back.dto.request.UserDietData;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputUserDietDetailRequestDto {

    @NotBlank
    private String food_name;

    @NotBlank
    private String food_first_category;

    private double chocdf;

    private double prot;

    private double fat;

    private double enerc;

}
