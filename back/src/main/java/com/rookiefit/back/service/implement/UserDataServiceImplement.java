package com.rookiefit.back.service.implement;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.common.CurrentDate;
import com.rookiefit.back.dto.request.userData.GetUserBodyDataRequestDto;
import com.rookiefit.back.dto.request.userData.GetUserProfileRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserBodyDataRequestDto;
import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.dto.response.userData.GetUserBodyDataResponseDto;
import com.rookiefit.back.dto.response.userData.GetUserProfileResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserBodyDataResponseDto;
import com.rookiefit.back.dto.response.userData.InputUserProfileResponseDto;
import com.rookiefit.back.entity.UserBodyDataEntity;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserBodyDataRepository;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.service.UserDataService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserDataServiceImplement implements UserDataService {

    private final UserProfileRepository userProfileRepository;
    private final UserBodyDataRepository userBodyDataRepository;
    private final JwtProvider jwtProvider;

    @Override
    public ResponseEntity<? super InputUserProfileResponseDto> inputUserProfile(InputUserProfileRequestDto dto) {
            String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());

            boolean isExsitedId = userProfileRepository.existsByUserAuthEntity_UserId(currentUserId);
            if(isExsitedId){
                userProfileRepository.deleteAllByUserAuthEntity_UserId(currentUserId);

            }
            dto.setToken(currentUserId);

            UserProfileEntity userProfileEntity = new UserProfileEntity(dto);
            userProfileRepository.save(userProfileEntity);
            return InputUserProfileResponseDto.succes();
    }

    @Override
    public ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(GetUserProfileRequestDto dto) {
            String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());

            boolean isExsitedId = userProfileRepository.existsByUserAuthEntity_UserId(currentUserId);
            if(!isExsitedId){System.out.println("아이디 존재하지 않음");}

            UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
            return GetUserProfileResponseDto.success(userProfileEntity);

    }

        @Override
        public ResponseEntity<? super InputUserBodyDataResponseDto> inputUserBodyData(InputUserBodyDataRequestDto dto) {
        String currentId = jwtProvider.getUserIdFromToken(dto.getToken());
        String currentDate = dto.getInbodydate();
        boolean isExsitedId = userBodyDataRepository.existsByInbodydate(currentDate);
        if (isExsitedId) {
                userBodyDataRepository.deleteAllByInbodydate(currentDate);
        }
        dto.setToken(currentId);
        // UserProfileEntity 조회 (현재 userId를 기반으로 UserProfileEntity를 찾기)
        UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentId);
        UserBodyDataEntity userBodyDataEntity = new UserBodyDataEntity(dto);
        userBodyDataEntity.setUserProfileData(userProfileEntity);

        userBodyDataRepository.save(userBodyDataEntity);
        return InputUserBodyDataResponseDto.success();
        }

        @Override
        public ResponseEntity<List<GetUserBodyDataResponseDto>> getUserBodyData(GetUserBodyDataRequestDto dto) {
                String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
                boolean isExsitedId = userBodyDataRepository.existsByUserProfile_UserAuthEntity_UserId(currentUserId);
                if(!isExsitedId){System.out.println("아이디가존재하지않음");};

                List<UserBodyDataEntity> responseDtos = userBodyDataRepository.findUserBodyDataByUserId(currentUserId);
                return GetUserBodyDataResponseDto.success(responseDtos);
        }

}
