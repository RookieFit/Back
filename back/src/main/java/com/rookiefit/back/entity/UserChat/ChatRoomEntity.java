package com.rookiefit.back.entity.UserChat;

import java.util.List;
import java.util.stream.Collectors;

import com.rookiefit.back.dto.UserChatDto.ChatRoomDto;
import com.rookiefit.back.entity.UserEntity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "chat_room")
@NoArgsConstructor
public class ChatRoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomName;

    @ManyToMany
    @JoinTable(
        name = "chat_room_users",
        joinColumns = @JoinColumn(name = "chat_room_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> participants;

    @OneToMany(mappedBy = "chatRoom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessageEntity> messages;

    public ChatRoomEntity(String roomName) {
        this.roomName = roomName;
    }

    public static ChatRoomDto fromEntity(ChatRoomEntity entity) {
        ChatRoomDto dto = new ChatRoomDto();
        dto.setId(entity.getId());
        dto.setRoomName(entity.getRoomName());
        dto.setParticipants(entity.getParticipants().stream()
            .map(UserEntity::getUserId)  // participants 목록을 사용자의 ID로 변환
            .collect(Collectors.toList()));
        return dto;
    }
}
