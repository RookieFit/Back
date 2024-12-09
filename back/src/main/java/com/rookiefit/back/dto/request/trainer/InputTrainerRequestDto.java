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

    private String licenseNumber;

    private MultipartFile licenseImageUrl;

    @NotBlank
    private String businessRegisterNumber;

    private MultipartFile businessRegisterImageUrl;
}
