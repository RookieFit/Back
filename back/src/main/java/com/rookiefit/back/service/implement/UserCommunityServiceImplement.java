package com.rookiefit.back.service.implement;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.request.userCommunity.UserCommunityAnswerRequestDto;
import com.rookiefit.back.dto.request.userCommunity.UserCommunityRequestDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityAnswerResponseDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityResponseDto;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.entity.UserCommunity.UserCommunityEntity;
import com.rookiefit.back.entity.UserCommunity.UserCommunity_Answer_ListEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.repository.UserCommunity.UserCommunityAnswerRepository;
import com.rookiefit.back.repository.UserCommunity.UserCommunityRepository;
import com.rookiefit.back.service.UserCommunityService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserCommunityServiceImplement implements UserCommunityService{

    private final JwtProvider jwtProvider;
    private final UserCommunityRepository userCommunityRepository;
    private final UserCommunityAnswerRepository userCommunityAnswerRepository;
    private final UserProfileRepository userProfileRepository;

    @Transactional
    @Override
    public ResponseEntity<? super UserCommunityResponseDto> inputUserCommunity(UserCommunityRequestDto dto){
        String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken()); // 토큰에서 userId 추출
        UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
        if (userProfileEntity == null) {
            return UserCommunityResponseDto.idNotFound();
        }
        System.out.println("1");
        if(dto.getCommunityListId() != null){
            Optional<UserCommunityEntity> optionalUserCommunity = userCommunityRepository.findById(dto.getCommunityListId());
            if (!optionalUserCommunity.isEmpty()) {
                UserCommunityEntity userCommunityEntity = optionalUserCommunity.get();
                userCommunityEntity.setCommunityContent(dto.getCommunityContent()); // 내용 수정s
                userCommunityEntity.setCommunityTitle(dto.getCommunityTitle()); // 제목 수정
                userCommunityEntity.setCommunityImageUrl(dto.getCommunityImageUrl()); // 이미지 URL 수정
                userCommunityEntity.setIsModified(true); // 수정 여부 표시
                System.out.println("2");
                userCommunityRepository.save(userCommunityEntity);
            }else{
                return UserCommunityAnswerResponseDto.communityListIdNotFound();
            }
        }else {
            System.out.println("3");
            UserCommunityEntity userCommunityEntity = new UserCommunityEntity(dto,userProfileEntity);
            userCommunityRepository.save(userCommunityEntity);
        } 
        return UserCommunityResponseDto.success();
    }

    @Override
    public ResponseEntity<? super UserCommunityAnswerResponseDto> inputUserCommunityAnswer(UserCommunityAnswerRequestDto dto) {
            String currentUserId = jwtProvider.getUserIdFromToken(dto.getToken());
            dto.setToken(currentUserId);
            Optional<UserCommunityEntity> optionaluserCommunity = userCommunityRepository.findById(dto.getCommunityListId());

            if(optionaluserCommunity.isEmpty()){
                return UserCommunityAnswerResponseDto.communityListIdNotFound();
            }
            UserCommunityEntity userCommunity = optionaluserCommunity.get();

            if(dto.getCommunityAnswerListId() != null){
                Optional<UserCommunity_Answer_ListEntity> optionalAnswerEntity = userCommunityAnswerRepository.findById(dto.getCommunityAnswerListId());
                if (optionalAnswerEntity.isEmpty()) {
                    return ResponseEntity.badRequest().body("Answer not found for update");
                }
                UserCommunity_Answer_ListEntity existingAnswer = optionalAnswerEntity.get();
                existingAnswer.setAnswerContent(dto.getAnswerContent());
                existingAnswer.setAnswerIsModified(true);
                userCommunityAnswerRepository.save(existingAnswer);
            }else{
                UserCommunity_Answer_ListEntity answerEntity = new UserCommunity_Answer_ListEntity(dto,userCommunity);
                userCommunityAnswerRepository.save(answerEntity);
            }
            
        return UserCommunityAnswerResponseDto.success();
    }
}
