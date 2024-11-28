package com.rookiefit.back.dto.request.admin;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LicenseApproveRequestDto {

    private List<Business> businesses; // businesses 배열을 필드로 추가

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Business {
        @NotBlank
        private List<String> b_no; // 사업자번호

    }
}
