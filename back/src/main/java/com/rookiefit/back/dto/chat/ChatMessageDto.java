package com.rookiefit.back.dto.chat;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDto {
    private Long chatRoomId;  // 메시지가 속한 채팅방 ID
    private String senderUserId;  // 메시지 보낸 사람의 userId
    private String content;  // 메시지 내용
    private String timestamp;  // 메시지 전송 시간 (필요시)
}
