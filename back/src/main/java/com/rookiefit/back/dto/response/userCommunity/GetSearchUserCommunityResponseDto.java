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
public class GetSearchUserCommunityResponseDto extends ResponseDto{
    private Long communityListId;
    private String communityTitle;
    private String communityContent;
    private String userNickname; // 작성자
    private LocalDateTime createdDate;

    public GetSearchUserCommunityResponseDto(UserCommunityEntity entity) {
        this.communityListId = entity.getCommunityListId();
        this.communityTitle = entity.getCommunityTitle();
        this.communityContent = entity.getCommunityContent();
        this.userNickname = entity.getCommunityAuthor();
        this.createdDate = entity.getCreatedDate();
    }

    public static ResponseEntity<List<GetSearchUserCommunityResponseDto>> success(List<UserCommunityEntity> communityEntities)  {
        List<GetSearchUserCommunityResponseDto> responseBody = communityEntities.stream()
            .map(GetSearchUserCommunityResponseDto::new) // DTO 생성자를 이용해 변환
            .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
} 
