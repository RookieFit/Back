package com.rookiefit.back.service;

import java.util.List;

import com.rookiefit.back.dto.UserChatDto.ChatMessageDto;
import com.rookiefit.back.dto.UserChatDto.ChatRoomDto;

public interface UserChatService {
    ChatRoomDto createChatRoom(ChatRoomDto dto);
    List<ChatMessageDto> getMessages(Long chatRoomId);  // 메시지 조회
    void sendMessage(ChatMessageDto dto);
    List<ChatMessageDto> getRecentMessages(Long chatRoomId);
}
