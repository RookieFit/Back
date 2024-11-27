package com.rookiefit.back.dto.response.userCommunity;

import java.time.LocalDateTime;

import com.rookiefit.back.dto.response.ResponseDto;
import com.rookiefit.back.entity.UserCommunity.UserCommunity_Answer_ListEntity;

import lombok.Getter;

@Getter
public class GetUserCommnityAnswerResponseDto extends ResponseDto{
    private String answerContent;
    private LocalDateTime answerCreatedDate;
    private Boolean answerIsModified;
    private String answerAuthor;

    public GetUserCommnityAnswerResponseDto(UserCommunity_Answer_ListEntity userCommunityAnswerList) {
        this.answerContent = userCommunityAnswerList.getAnswerContent();
        this.answerCreatedDate = userCommunityAnswerList.getAnswerCreatedDate();
        this.answerIsModified = userCommunityAnswerList.getAnswerIsModified();
        this.answerAuthor = userCommunityAnswerList.getAnswerAuthor();
    }
}
