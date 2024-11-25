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
public class GetUserCommunityResponseDto extends ResponseDto{

    private Long communityListId;
    private String communityTitle;
    private String communityContent;
    private String communityImageUrl;
    private String communityContentType;
    private LocalDateTime createdDate;

    // Entity -> DTO 변환 생성자
    public GetUserCommunityResponseDto(UserCommunityEntity entity) {
        this.communityListId = entity.getCommunityListId();
        this.communityTitle = entity.getCommunityTitle();
        this.communityContent = entity.getCommunityContent();
        this.communityImageUrl = entity.getCommunityImageUrl();
        this.communityContentType = entity.getCommunityContentType();
        this.createdDate = entity.getCreatedDate();
    }

    public static ResponseEntity<List<GetUserCommunityResponseDto>> success(List<UserCommunityEntity> userCommunityList) {
        List<GetUserCommunityResponseDto> responseList = userCommunityList.stream()
                .map(GetUserCommunityResponseDto::new) // Entity를 DTO로 변환
                .collect(Collectors.toList()); // List로 수집
        return ResponseEntity.status(HttpStatus.OK).body(responseList); // ResponseEntity 반환
    }
}
