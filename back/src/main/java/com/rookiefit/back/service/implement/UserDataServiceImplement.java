package com.rookiefit.back.service.implement;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.common.CurrentDate;
import com.rookiefit.back.dto.request.userData.GetUserBodyDataRequestDto;
import com.rookiefit.back.dto.request.userData.GetUserProfileRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserBodyDataRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.dto.response.userData.GetUserBodyDataResponseDto;
import com.rookiefit.back.dto.response.userData.GetUserProfileResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserBodyDataResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserProfileResponseDto;
import com.rookiefit.back.entity.UserBodyDataEntity;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserBodyDataRepository;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.service.UserDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDataServiceImplement implements UserDataService{

    private final UserProfileRepository userProfileRepository;
    private final UserBodyDataRepository userBodyDataRepository;
    private final UserProfileEntity userProfileEntity;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<? super InputUserProfileResponseDto> inputUserProfile(InputUserProfileRequestDto dto) {
        try {
            String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
            dto.setToken(currentUserId);

            UserProfileEntity userProfileEntity = new UserProfileEntity(dto);
            userProfileRepository.save(userProfileEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return InputUserProfileResponseDto.succes();
    }

    @Override
    public ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(GetUserProfileRequestDto dto) {
        try {
            String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
            boolean isExsitedId = userProfileRepository.existsByUserId(currentUserId);
            if(!isExsitedId){System.out.println("아이디 존재하지 않음");}

            UserProfileEntity userProfileEntity = userProfileRepository.findByUserId(currentUserId);
            return GetUserProfileResponseDto.success(userProfileEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }

    @Override
    public ResponseEntity<? super InputUserBodyDataResponseDto> inputUserBodyData(InputUserBodyDataRequestDto dto) {
        try {
            String currentId = jwtProvider.getUserIdFromToken(dto.getToken());
            dto.setToken(currentId);
            
            UserBodyDataEntity userBodyDataEntity = new UserBodyDataEntity(dto);
            userBodyDataRepository.save(userBodyDataEntity);
            
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return InputUserBodyDataResponseDto.success();
    }

    @Override
    public ResponseEntity<? super GetUserBodyDataResponseDto> getUserBodyData(GetUserBodyDataRequestDto dto) {
        try {
            String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
            boolean isExsitedId = userBodyDataRepository.existsByUserId(currentUserId);
            if(!isExsitedId){System.out.println("아이디가존재하지않음");};

            List<UserBodyDataEntity> userBodyDataEntity = userBodyDataRepository.findByUserId(currentUserId);
            return GetUserBodyDataResponseDto.success(userBodyDataEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
    }
    
}
