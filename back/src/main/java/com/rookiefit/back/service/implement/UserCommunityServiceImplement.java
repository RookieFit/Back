package com.rookiefit.back.service.implement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.rookiefit.back.dto.request.userCommunity.UserCommunityAnswerRequestDto;
import com.rookiefit.back.dto.request.userCommunity.UserCommunityRequestDto;
import com.rookiefit.back.dto.response.userCommunity.DeleteUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetAllUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetSearchUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.GetByContentTypeUserCommunityResponseDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityAnswerResponseDto;
import com.rookiefit.back.dto.response.userCommunity.UserCommunityResponseDto;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.entity.UserCommunity.CommunityImageListEntity;
import com.rookiefit.back.entity.UserCommunity.UserCommunityEntity;
import com.rookiefit.back.entity.UserCommunity.UserCommunity_Answer_ListEntity;
import com.rookiefit.back.provider.JwtProvider;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.repository.UserCommunity.CommunityImageListRepository;
import com.rookiefit.back.repository.UserCommunity.UserCommunityAnswerRepository;
import com.rookiefit.back.repository.UserCommunity.UserCommunityRepository;
import com.rookiefit.back.service.FirebaseService;
import com.rookiefit.back.service.UserCommunityService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserCommunityServiceImplement implements UserCommunityService{

    private final JwtProvider jwtProvider;
    private final UserCommunityRepository userCommunityRepository;
    private final UserCommunityAnswerRepository userCommunityAnswerRepository;
    private final CommunityImageListRepository communityImageListRepository;
    private final FirebaseService firebaseService;
    private final UserProfileRepository userProfileRepository;

    @Transactional
    @Override
    public ResponseEntity<? super UserCommunityResponseDto> inputUserCommunity(UserCommunityRequestDto dto, String currentUserId){
        UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
        if (userProfileEntity == null) {
            return UserCommunityResponseDto.idNotFound();
        }
        List<String> communityImages = new ArrayList<>();
        // 파일 업로드 처리
        if (dto.getCommnunityImages() != null && dto.getCommnunityImages().length > 0) {
            List<MultipartFile> fileList = Arrays.asList(dto.getCommnunityImages());
            try {
                communityImages = firebaseService.uploadFiles(fileList); // Firebase에 업로드 후 URI 리스트 반환
            } catch (IOException exception) {
                exception.printStackTrace();
            }
        }
        //이미지와 커뮤니티 리스트 저장
        UserCommunityEntity userCommunityEntity = new UserCommunityEntity(dto,userProfileEntity);
        userCommunityRepository.save(userCommunityEntity);
        for (String imageUri : communityImages) {
            CommunityImageListEntity communityImageEntity = new CommunityImageListEntity(imageUri);
            communityImageEntity.setUserCommunity(userCommunityEntity);  // 커뮤니티와 연관 설정
            communityImageListRepository.save(communityImageEntity);  // 이미지 저장
        }
        return UserCommunityResponseDto.success();
    }

    //usercommunity input 와 update 분리 완(241204-11:26_김민준)
    //이미지 업데이트 추가 완(241204-14:15_김민준)
    @Override
    @Transactional
    public ResponseEntity<? super UserCommunityResponseDto> updateUserCommunity(UserCommunityRequestDto dto, Long userCommunityId, String currentUserId) {
        UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);// profile에서 존재하는 userid인지 체크
        if (userProfileEntity == null) {
            return UserCommunityResponseDto.idNotFound();
        }

        Optional<UserCommunityEntity> optionalUserCommunity = userCommunityRepository.findById(userCommunityId); // 게시물 id로 해당하는 게시물 찾기
        if (optionalUserCommunity.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User Community not found with ID: " + userCommunityId);
        }

        UserCommunityEntity userCommunityEntity = optionalUserCommunity.get();
        userCommunityEntity.update(dto,userProfileEntity);// 게시물 업데이트

        // 기존 이미지 삭제
        List<CommunityImageListEntity> existingImages =
                communityImageListRepository.findByUserCommunity_CommunityListId(userCommunityId);// 게시물 id에 해당하는 이미지리스트 찾기
        if(existingImages != null){
            for (CommunityImageListEntity image : existingImages) { // 이미지리스트가 존재한다면 
                communityImageListRepository.delete(image); // DB에서 삭제
                firebaseService.deleteFile(image.getCommunityImageUri()); // Firebase에서 삭제
            }
        }
        // 새 이미지 업로드 및 저장
        if (dto.getCommnunityImages() != null && dto.getCommnunityImages().length > 0) {
            List<MultipartFile> fileList = Arrays.asList(dto.getCommnunityImages()); // 이미지 파일들 리스트화
            try {
                List<String> uploadedUrls = firebaseService.uploadFiles(fileList); // Firebase 업로드 후 이미지 주소 반환
                for (String url : uploadedUrls) {// 이미지 DB에 저장
                    CommunityImageListEntity newImage = new CommunityImageListEntity(url);
                    newImage.setUserCommunity(userCommunityEntity);
                    communityImageListRepository.save(newImage);
                }
            } catch (IOException exception) {
                exception.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body("Image upload failed");
            }
        }
        userCommunityRepository.save(userCommunityEntity); // 게시물 최종 저장
        return UserCommunityResponseDto.success(); // 성공 메세지 반환
    }


    //todo : 댓글도 인풋과 수정을 분리
    @Override
    public ResponseEntity<? super UserCommunityAnswerResponseDto> inputUserCommunityAnswer(UserCommunityAnswerRequestDto dto, String currentUserId) {
        UserProfileEntity userProfileEntity = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
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
            UserCommunity_Answer_ListEntity answerEntity = new UserCommunity_Answer_ListEntity(dto,userCommunity,userProfileEntity);
            userCommunityAnswerRepository.save(answerEntity);
        }
        
        return UserCommunityAnswerResponseDto.success();
    }

    @Override
    public ResponseEntity<List<GetAllUserCommunityResponseDto>> getAllUserCommunity() {
        List<UserCommunityEntity> communityEntities = userCommunityRepository.findAll();
        return GetAllUserCommunityResponseDto.success(communityEntities);
    }

    @Override
    public ResponseEntity<List<GetByContentTypeUserCommunityResponseDto>> getByContentTypeUserCommunity(String communityContentType) {
        List<UserCommunityEntity> userCommunityList = userCommunityRepository.findByCommunityContentType(communityContentType);
        return GetByContentTypeUserCommunityResponseDto.success(userCommunityList);
    }

    @Override
    public ResponseEntity<List<GetSearchUserCommunityResponseDto>> getSearchUserCommunity(String keyword, String field) {
        List<UserCommunityEntity> userCommunityList;
        // field 값에 따라 검색
        if ("title".equals(field)) {
            userCommunityList = userCommunityRepository.findByCommunityTitleContaining(keyword);
        } else if ("content".equals(field)) {
            userCommunityList = userCommunityRepository.findByCommunityContentContaining(keyword);
        } else if ("author".equals(field)) {
            userCommunityList = userCommunityRepository.findByCommunityAuthorContaining(keyword);
        } else {
            // field 값이 잘못된 경우 예외 처리
            return ResponseEntity.badRequest().build();
        }
        // UserCommunityEntity를 GetSearchUserCommunityResponseDto로 변환
        List<GetSearchUserCommunityResponseDto> responseList = userCommunityList.stream()
            .map(GetSearchUserCommunityResponseDto::new) // 엔티티에서 DTO로 변환하는 생성자 사용
            .toList();
        // 성공 응답 반환
        return ResponseEntity.ok(responseList);
    }

    @Override
    public ResponseEntity<? super GetUserCommunityResponseDto> getUserCommunity(Long id) {
        // 해당 게시물 조회
        Optional<UserCommunityEntity> usercommnunity = userCommunityRepository.findById(id);
        if (usercommnunity == null) {
            // 게시물이 없는 경우 404 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        // DTO 변환
        GetUserCommunityResponseDto response = new GetUserCommunityResponseDto(usercommnunity.get());
        return ResponseEntity.ok(response);
    }

    @Override
    public  ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunity(Long communityListId) {
        Optional<UserCommunityEntity> optionalEntity = userCommunityRepository.findById(communityListId);
        if (optionalEntity.isEmpty()) {
            return DeleteUserCommunityResponseDto.communityNotFound();
        }
        userCommunityRepository.deleteById(communityListId);
        return DeleteUserCommunityResponseDto.success();
    }

    @Override
    public  ResponseEntity<? super DeleteUserCommunityResponseDto> deleteUserCommunityAnswer(Long communityAnswerListId) {
        Optional<UserCommunity_Answer_ListEntity> optionalEntity = userCommunityAnswerRepository.findById(communityAnswerListId);
        if (optionalEntity.isEmpty()) {
            return DeleteUserCommunityResponseDto.communityNotFound();
        }
        userCommunityAnswerRepository.deleteById(communityAnswerListId);
        return DeleteUserCommunityResponseDto.success();
    }
}