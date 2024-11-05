package com.rookiefit.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.IdCheckRequestDto;
import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.auth.IdCheckResponseDto;
import com.rookiefit.back.repository.UserRepository;
import com.rookiefit.back.service.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImplement implements AuthService {

    private final UserRepository userRepository;
    
    @Override
    public ResponseEntity<? super IdCheckResponseDto> idCheck( IdCheckRequestDto dto ) {
        try {

            String userId = dto.getUserId();
            boolean isExistId = userRepository.existsByUserId(userId);
            if( isExistId )return IdCheckResponseDto.duplicatedId();

        } catch (Exception exception) {
            exception.printStackTrace();
            System.out.println(dto.getUserId());
            return ResponseDto.databaseError();
        }

        return IdCheckResponseDto.success();
    }
}
