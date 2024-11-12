package com.rookiefit.back.service.implement;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDataRequestDto;
import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutDataResponseDto;
import com.rookiefit.back.entity.UserWorkoutListDataEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserWorkoutListDataRepository;
import com.rookiefit.back.service.UserWorkoutDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserWorkoutDataServiceImplement implements UserWorkoutDataService{

    private final JwtProvider jwtProvider;
    private final UserWorkoutListDataRepository userWorkoutListDataRepository;

    @Override
    public ResponseEntity<? super InputUserWorkoutDataResponseDto> inputUserWorkoutData(InputUserWorkoutDataRequestDto dto) {
        try {
            String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());//token에서 userId 추출
            dto.setToken(currentUserId);//userId 저장

            UserWorkoutListDataEntity userWorkoutListDataEntity = new UserWorkoutListDataEntity(dto);
            userWorkoutListDataRepository.save(userWorkoutListDataEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return InputUserWorkoutDataResponseDto.success();
    }
}
