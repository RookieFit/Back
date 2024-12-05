package com.rookiefit.back.entity;

import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.stereotype.Component;

import com.rookiefit.back.dto.request.userData.InputUserProfileRequestDto;
import com.rookiefit.back.entity.Market.MarketItemListEntity;
import com.rookiefit.back.entity.UserChat.UserChatMessageEntity;
import com.rookiefit.back.entity.UserChat.UserChatRoomEntity;
import com.rookiefit.back.entity.UserCommunity.UserCommunityEntity;
import com.rookiefit.back.entity.UserCommunity.UserCommunity_Answer_ListEntity;
import com.rookiefit.back.entity.UserDiet.UserDietListDataEntity;
import com.rookiefit.back.entity.UserWorkout.UserWorkoutListDataEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_profile")
public class UserProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long userProfileId;

    @NotBlank
    private String userProfileImageUri;

    private String gymName;

    private String userMessage;

    private String userName;

    private String userAddress;

    private String userNickname;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "userId", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userAuthEntity;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserBodyDataEntity> userBodyDatas;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserDietListDataEntity> userDietLists;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<UserWorkoutListDataEntity> userWorkoutLists;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY )
    private List<UserCommunityEntity> userCommunityEntities;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY )
    private List<UserCommunity_Answer_ListEntity> userCommunityAnswerListEntities;

    @OneToMany(mappedBy = "userProfile", cascade = CascadeType.REMOVE,fetch = FetchType.LAZY )
    private List<MarketItemListEntity> MarketItemEntities;

    @ManyToMany(mappedBy = "participants")
    private List<UserChatRoomEntity> chatRooms;  // 사용자가 참여한 채팅방들

    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserChatMessageEntity> messages;  // 사용자가 보낸 메시지들

    //241205-09:32_김민준 : dto로 넘어오는 값들이 null로 검사하는게 아닌 isEmpty로 검사해야 빈값이 아닌 기본값을 넣을 수 있음
    public UserProfileEntity(InputUserProfileRequestDto dto, String uploadedFileUrl, UserEntity userEntity) {
        this.userAuthEntity = userEntity;// userId 설정
        this.userProfileImageUri = uploadedFileUrl;
        this.gymName = dto.getGymName();
        this.userMessage = dto.getUserMessage();
        this.userName = dto.getUserName();
        this.userAddress = dto.getUserAddress();
        this.userNickname = dto.getUserNickname();
    }
}