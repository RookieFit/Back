package com.rookiefit.back.dto.request.userCommunity;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserCommunityAnswerRequestDto {
        @NotNull
        private Long communityListId;

        private Long communityAnswerListId;

        @NotBlank
        private String answerContent;
        
        private LocalDateTime answerCreatedDate;

        private Boolean answerIsModified;

}
