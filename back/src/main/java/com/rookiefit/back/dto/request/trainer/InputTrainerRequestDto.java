package com.rookiefit.back.dto.request.trainer;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputTrainerRequestDto {

    @NotBlank
    private String licenseImageUrl;

    @NotBlank
    private String licenseNumber;

    @NotBlank
    private String businessRegisterNumber;

    @NotBlank
    private String businessRegisterImageUrl;
}
