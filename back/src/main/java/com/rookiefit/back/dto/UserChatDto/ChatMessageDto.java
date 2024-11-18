package com.rookiefit.back.dto.UserChatDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatMessageDto {
    private String senderId;
    private String content;
    private Long chatRoomId;

    // 생성자 및 getter, setter
    public ChatMessageDto (String senderId, String content, Long chatRoomId) {
        this.senderId = senderId;
        this.content = content;
        this.chatRoomId = chatRoomId;
    }

}
