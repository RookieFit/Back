package com.rookiefit.back.entity.UserCommunity;

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
@Table(name="community_image_list")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommunityImageListEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // AUTO_INCREMENT 설정
    @Column(name = "community_image_id")
    private Long communityImageId;

    @Column(name = "community_image_uri")
    private String communityImageUri;  // 이미지 URI (주소)

    @ManyToOne(fetch = FetchType.EAGER)  // Many to One 관계 설정 (다수의 이미지는 하나의 커뮤니티와 관련)
    @JoinColumn(name = "community_list_id", referencedColumnName = "community_list_id", nullable = false)
    private UserCommunityEntity userCommunity;  // user_community 엔티티와 연관

    public CommunityImageListEntity(String communityImageUri) {
        this.communityImageUri = communityImageUri;
    }
}