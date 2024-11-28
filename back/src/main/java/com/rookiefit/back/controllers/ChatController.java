package com.rookiefit.back.controllers;


import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.rookiefit.back.dto.chat.ChatMessageDto;
import com.rookiefit.back.dto.chat.ChatRoomDto;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.service.UserChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final UserChatService userChatService;
    private final SimpMessagingTemplate simpMessagingTemplate;

    // 채팅방 생성
    @MessageMapping("/createchatroom")
    @SendTo("/topic/chatroom")  // /topic/chatroom 경로로 메시지를 보내기 위해 @SendTo 사용
    public Long createChatRoom(ChatRoomDto chatRoomDto, SimpMessageHeaderAccessor headerAccessor) {
        UserEntity sessionUserId = (UserEntity) headerAccessor.getSessionAttributes().get("user");
        String currentUserId = sessionUserId.getUserId();
        System.out.println("Current User ID: " + currentUserId);

        // 채팅방 생성 서비스 호출
        Long createdChatRoomId = userChatService.createChatRoom(chatRoomDto, currentUserId);
        System.out.println("Created Chat Room ID: " + createdChatRoomId);

        // 채팅방 ID 반환 (클라이언트로 전송)
        return createdChatRoomId;
    }

    // 메시지 전송
    @MessageMapping("/sendmessage")
    public void sendMessage(ChatMessageDto chatMessageDto, SimpMessageHeaderAccessor headerAccessor) {
        UserEntity sessionUserId = (UserEntity) headerAccessor.getSessionAttributes().get("user");
        String currentUserId = sessionUserId.getUserId();
        chatMessageDto.setSenderUserId(currentUserId);

        // 채팅방 ID를 통해 메시지 전송
        Long chatRoomId = chatMessageDto.getChatRoomId();  // 클라이언트에서 받은 채팅방 ID
        userChatService.sendMessage(chatMessageDto);

        // 해당 채팅방으로 메시지 전송
        simpMessagingTemplate.convertAndSend("/topic/"+chatRoomId, chatMessageDto);
    }

    // 채팅방 삭제
    @MessageMapping("/deletechatroom")
    public void deleteChatRoom(Long chatRoomId) {
        userChatService.deleteChatRoom(chatRoomId);
    }


}