package com.rookiefit.back.entity.UserChat;


import com.rookiefit.back.entity.UserProfileEntity;

import jakarta.persistence.Entity;
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
@Getter
@Setter
@Table(name = "user_chat_message")
@AllArgsConstructor
@NoArgsConstructor
public class UserChatMessageEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long messageId;  // 메시지 ID

    private String content;  // 메시지 내용

    @ManyToOne
    @JoinColumn(name = "chat_room_id", nullable = false)
    private UserChatRoomEntity chatRoom;  // 메시지가 속한 채팅방

    @ManyToOne
    @JoinColumn(name = "user_profile_id", nullable = false)
    private UserProfileEntity sender;  // 메시지를 보낸 사용자 (프로필)

    private String timestamp;  // 메시지 발송 시간 (예: "2024-11-28T12:00:00")

    // 생성자와 다른 필요한 메서드들
    public UserChatMessageEntity(String content, UserChatRoomEntity chatRoom, UserProfileEntity sender) {
        this.content = content;
        this.chatRoom = chatRoom;
        this.sender = sender;
        this.timestamp = java.time.LocalDateTime.now().toString();  // 현재 시간으로 설정
    }
}