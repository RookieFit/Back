package com.rookiefit.back.service;

import com.rookiefit.back.dto.request.admin.LicenseApproveRequestDto;
import com.rookiefit.back.dto.response.admin.LicenseApproveResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LicenseStatusService {
    ResponseEntity<List<LicenseApproveResponseDto>> checkLicenseStatus(LicenseApproveRequestDto requestDto);
}
