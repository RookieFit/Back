package com.rookiefit.back.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.rookiefit.back.dto.UserChatDto.ChatMessageDto;
import com.rookiefit.back.dto.UserChatDto.ChatRoomDto;
import com.rookiefit.back.repository.UserChat.ChatMessageRepository;
import com.rookiefit.back.repository.UserChat.ChatRoomRepository;
import com.rookiefit.back.service.UserChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final UserChatService userChatService;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    @SubscribeMapping("/topic/public")
    public void onSubscribe() {
        System.out.println("Subscription to /topic/public received.");
    }
    
    @MessageMapping("/sendmessage")
    @SendTo("/topic/public")
    public ChatMessageDto sendMessage(@Payload ChatMessageDto chatMessageDto) {
        userChatService.sendMessage(chatMessageDto);
        return chatMessageDto;  // @SendTo에 의해 자동으로 메시지가 전송됩니다.
    }

    @MessageMapping("/createChatRoom")
    public List<ChatMessageDto> fetchMessagesForRoom(ChatRoomDto chatRoomDto) {
        System.out.println("controller task");
        Long chatRoomId = chatRoomDto.getId();
        List<ChatMessageDto> messages = userChatService.getMessages(chatRoomId);
        return messages;  // 해당 채팅방의 메시지 목록 반환
    }
}
