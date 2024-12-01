package com.rookiefit.back.dto.response.userCommunity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserCommunity.UserCommunityEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllUserCommunityResponseDto extends ResponseDto{

    private Long communityListId;
    private String communityTitle;
    private String communityContent;
    private String communityImageUrl; 
    private String communityContentType;
    private LocalDateTime createdDate;

    public GetAllUserCommunityResponseDto(UserCommunityEntity entity) {
        this.communityListId = entity.getCommunityListId();
        this.communityTitle = entity.getCommunityTitle();
        this.communityContent = entity.getCommunityContent();
        this.communityImageUrl = entity.getCommunityImageUrl();
        this.communityContentType = entity.getCommunityContentType();
        this.createdDate = entity.getCreatedDate();
    }

    public static ResponseEntity<? super GetAllUserCommunityResponseDto>success(List<UserCommunityEntity> communityEntities)  {
        List<GetAllUserCommunityResponseDto> responseBody = communityEntities.stream()
            .map(GetAllUserCommunityResponseDto::new) // DTO 생성자를 이용해 변환
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }   
}
