package com.rookiefit.back.dto.response.userCommunity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserCommunity.UserCommunityEntity;
import lombok.Getter;

@Getter
public class GetUserCommunityResponseDto extends ResponseDto{
    private String communityTitle;
    private String communityContent;
    private LocalDateTime createdDate;
    private String communityAuthor;
    private String communityContentType;
    private boolean isModified;
    private List<GetUserCommnityAnswerResponseDto> userCommunityAnswerList;

    public GetUserCommunityResponseDto(UserCommunityEntity userCommunityEntity) {
            this.communityTitle = userCommunityEntity.getCommunityTitle();
            this.communityContent = userCommunityEntity.getCommunityContent();
            this.createdDate = userCommunityEntity.getCreatedDate();
            this.communityAuthor = userCommunityEntity.getCommunityAuthor();
            this.communityContentType = userCommunityEntity.getCommunityContentType();
            this.isModified = userCommunityEntity.getIsModified();
            this.userCommunityAnswerList = userCommunityEntity.
                                                    getUserCommunityAnswerLists()
                                                    .stream()
                                                    .map(GetUserCommnityAnswerResponseDto::new)
                                                    .toList();
    }
}
