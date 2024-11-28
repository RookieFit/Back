package com.rookiefit.back.entity.UserChat;

import java.util.ArrayList;
import java.util.List;

import com.rookiefit.back.entity.UserProfileEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "user_chat_room")
@AllArgsConstructor
@NoArgsConstructor
public class UserChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long chatRoomId;  // 채팅방  

    private String chatRoomName;  // 채팅방 이름

    @ManyToMany
    @JoinTable(
        name = "chat_room_participants", 
        joinColumns = @JoinColumn(name = "chat_room_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_profile_id")
    )
    private List<UserProfileEntity> participants = new ArrayList<>();  // 채팅방 참여자들 (여러 사용자가 참여 가능)

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserChatMessageEntity> messages;  // 해당 채팅방에서 보내진 메시지들

    public void addParticipant(UserProfileEntity participant) {
        this.participants.add(participant);
    }

    public void removeParticipant(UserProfileEntity participant) {
        this.participants.remove(participant);
    }
}
