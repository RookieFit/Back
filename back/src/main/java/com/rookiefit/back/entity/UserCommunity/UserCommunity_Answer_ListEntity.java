package com.rookiefit.back.entity.UserCommunity;

import java.time.LocalDateTime;

import com.rookiefit.back.dto.request.userCommunity.UserCommunityAnswerRequestDto;
import com.rookiefit.back.entity.UserProfileEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="user_community_answer_list")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserCommunity_Answer_ListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_answer_list_id")
    private Long communityAnswerListId;

    @Column(name = "answer_content")
    private String answerContent;

    @Column(name = "answer_createdDate", nullable = false)
    private LocalDateTime answerCreatedDate;

    @Column(name = "answer_isModified", nullable = false)
    private Boolean answerIsModified = false;

    @Column(name = "answer_author" , nullable = false)
    private String answerAuthor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "community_list_id", referencedColumnName = "community_list_id", nullable = false)
    private UserCommunityEntity userCommunity; // UserCommunity와의 관계 설정

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private UserProfileEntity userProfile; // UserProfile와의 관계 설정

    public UserCommunity_Answer_ListEntity(UserCommunityAnswerRequestDto dto, UserCommunityEntity userCommunity ,UserProfileEntity userProfileEntity) {
        this.answerContent = dto.getAnswerContent();
        this.answerCreatedDate = dto.getAnswerCreatedDate() != null ? dto.getAnswerCreatedDate() : LocalDateTime.now();//date값이 안들어 오면 현재값 입력
        this.answerIsModified = dto.getAnswerIsModified() != null ? dto.getAnswerIsModified() : false;
        this.answerAuthor =userProfileEntity.getUserNickname(); // 저자에 유저아이디 들어감
        this.userCommunity = userCommunity;
        this.userProfile=userProfileEntity;
    }
}