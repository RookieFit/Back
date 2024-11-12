package com.rookiefit.back.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.userWorkoutData.GetUserWorkoutListRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutDetailRequestDto;
import com.rookiefit.back.dto.request.userWorkoutData.InputUserWorkoutListRequestDto;
import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.userData.GetUserBodyDataResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.GetUserWorkoutListResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutDetailResponseDto;
import com.rookiefit.back.dto.response.userWorkoutData.InputUserWorkoutListResponseDto;
import com.rookiefit.back.entity.UserBodyDataEntity;
import com.rookiefit.back.entity.UserWorkoutDetailDataEntity;
import com.rookiefit.back.entity.UserWorkoutListDataEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserWorkoutDetailDataRepository;
import com.rookiefit.back.repository.UserWorkoutListDataRepository;
import com.rookiefit.back.service.UserWorkoutDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserWorkoutDataServiceImplement implements UserWorkoutDataService{

    private final JwtProvider jwtProvider;
    private final UserWorkoutListDataRepository userWorkoutListDataRepository;
    private final UserWorkoutDetailDataRepository userWorkoutDetailDataRepository;

    @Override
    public ResponseEntity<? super InputUserWorkoutListResponseDto> inputUserWorkoutData(InputUserWorkoutListRequestDto dto) {
        try {
            String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());//token에서 userId 추출
            dto.setToken(currentUserId);//userId 저장

            UserWorkoutListDataEntity userWorkoutListDataEntity = new UserWorkoutListDataEntity(dto);
            userWorkoutListDataRepository.save(userWorkoutListDataEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return InputUserWorkoutListResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetUserWorkoutListResponseDto> getUserWorkoutData(GetUserWorkoutListRequestDto dto) {
        try {
            String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
            boolean isExsitedId = userWorkoutListDataRepository.existsByUserId(currentUserId);
            if(!isExsitedId){System.out.println("아이디가존재하지않음");};

            List<UserWorkoutListDataEntity> userWorkoutListDataEntity = userWorkoutListDataRepository.findByUserId(currentUserId);
            return GetUserWorkoutListResponseDto.success(userWorkoutListDataEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super InputUserWorkoutDetailResponseDto> inputUserWorkoutDetail(InputUserWorkoutDetailRequestDto dto) {
        try {
            UserWorkoutDetailDataEntity userWorkoutDetailDataEntity = new UserWorkoutDetailDataEntity(dto);
            userWorkoutDetailDataRepository.save(userWorkoutDetailDataEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return InputUserWorkoutDetailResponseDto.success();
    }
}