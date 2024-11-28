package com.rookiefit.back.service;

import com.rookiefit.back.dto.chat.ChatMessageDto;
import com.rookiefit.back.dto.chat.ChatRoomDto;
import com.rookiefit.back.entity.UserChat.UserChatRoomEntity;

public interface UserChatService {
    
    Long createChatRoom(ChatRoomDto chatRoomDto , String currentUserId);

    void sendMessage(ChatMessageDto chatMessageDto);

    void deleteChatRoom(Long chatRoomId);
}
