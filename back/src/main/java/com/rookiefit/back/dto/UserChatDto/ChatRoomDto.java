package com.rookiefit.back.dto.UserChatDto;

import java.util.List;
import java.util.stream.Collectors;

import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.entity.UserChat.ChatRoomEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDto {
    private Long id;              // 채팅방 ID
    private String roomName;      // 채팅방 이름
    private List<String> participants;  // 채팅방 참가자 목록 (userId 리스트)

    // 엔티티에서 DTO로 변환하는 생성자
    public ChatRoomDto(Long id, String roomName, List<String> participants) {
        this.id = id;
        this.roomName = roomName;
        this.participants = participants;
    }

    // 엔티티에서 DTO로 변환하는 메서드
    public static ChatRoomDto fromEntity(ChatRoomEntity chatRoomEntity) {
        List<String> participantNames = chatRoomEntity.getParticipants().stream()
            .map(UserEntity::getUserId) // 참가자들의 userId만 가져옴
            .collect(Collectors.toList());

        return new ChatRoomDto(
            chatRoomEntity.getId(),
            chatRoomEntity.getRoomName(),
            participantNames
        );
    }
}
