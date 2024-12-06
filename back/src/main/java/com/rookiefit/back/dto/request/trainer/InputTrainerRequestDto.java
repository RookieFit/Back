package com.rookiefit.back.dto.request.trainer;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InputTrainerRequestDto {

    @NotBlank
    private String userId;

    private MultipartFile licenseImageUrl;

    @NotBlank
    private String licenseNumber;

    @NotBlank
    private String businessRegisterNumber;

    private MultipartFile businessRegisterImageUrl;
}
