package com.rookiefit.back.entity.UserCommunity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.querydsl.binding.QuerydslPredicate;

import com.rookiefit.back.dto.request.userCommunity.UserCommunityRequestDto;
import com.rookiefit.back.entity.UserProfileEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user_community")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@QuerydslPredicate
public class UserCommunityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "community_list_id")
    private Long communityListId;

    @Column(name = "community_title", nullable = false)
    private String communityTitle;

    @Column(name = "community_content", nullable = false)
    private String communityContent;

    @Column(name = "createdDate", nullable = false)
    private LocalDateTime createdDate;

    @Column(name = "isModified")
    private Boolean isModified=false;

    @Column(name = "community_image_url")
    private String communityImageUrl;

    @Column(name = "community_author")
    private String communityAuthor;

    @Column(name = "community_content_type", nullable = false)
    private String communityContentType;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserProfileEntity userProfile; // UserProfile과의 관계 설정

    // UserCommunityAnswerList와의 관계 설정 (1:N)
    @OneToMany(mappedBy = "userCommunity", fetch = FetchType.EAGER, cascade=CascadeType.REMOVE)
    private List<UserCommunity_Answer_ListEntity> userCommunityAnswerLists;

    @OneToMany(mappedBy = "userCommunity", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<CommunityImageListEntity> communityImages;
    
    public UserCommunityEntity(UserCommunityRequestDto dto , UserProfileEntity userProfileEntity) {
        this.communityTitle = dto.getCommunityTitle();
        this.communityContent = dto.getCommunityContent();
        this.createdDate = dto.getCreatedDate() != null ? dto.getCreatedDate() : LocalDateTime.now();//date값이 안들어 오면 현재값 입력
        this.isModified = dto.getIsModified() != null ? dto.getIsModified() : false;
        this.communityImageUrl = dto.getCommunityImageUrl();
        this.communityContentType = dto.getCommunityContentType();
        this.communityAuthor = userProfileEntity.getUserNickname();
        this.userProfile = userProfileEntity;
    }

    public void update(UserCommunityRequestDto dto, UserProfileEntity userProfileEntity) {
        this.communityTitle = dto.getCommunityTitle() != null ? dto.getCommunityTitle() : communityTitle;
        this.communityContent = dto.getCommunityContent() != null ? dto.getCommunityTitle() : communityContent;
        this.isModified = dto.getIsModified() != null ? dto.getIsModified() : true;
        this.communityImageUrl = dto.getCommunityImageUrl() != null ? dto.getCommunityImageUrl() : communityImageUrl;
        this.communityContentType = dto.getCommunityContentType() != null ? dto.getCommunityContentType() : communityContentType;
        this.userProfile = userProfileEntity;
    }

    public void addCommunityImages(List<String> imageUris) {
        for (String imageUri : imageUris) {
            CommunityImageListEntity imageEntity = new CommunityImageListEntity(imageUri);
            imageEntity.setUserCommunity(this);
            this.communityImages.add(imageEntity);
        }
    }

}
