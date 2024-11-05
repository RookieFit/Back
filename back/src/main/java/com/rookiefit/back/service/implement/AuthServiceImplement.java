package com.rookiefit.back.service.implement;

import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.request.IdCheckRequestDto;
import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.auth.IdCheckResponseDto;

public class AuthServiceImplement {
    
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idcheck( IdCheckRequestDto dto ) {
        try {
            
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
}
