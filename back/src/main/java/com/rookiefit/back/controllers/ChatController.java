package com.rookiefit.back.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

    private final UserChatService userChatService;
    private final ChatMessageRepository chatMessageRepository;
    private final ChatRoomRepository chatRoomRepository;

    @MessageMapping("/sendmessage")
    @SendTo("/topic/public")
     public ResponseEntity<Void> sendMessage(@RequestBody ChatMessageDto chatMessageDto) {
        userChatService.sendMessage(chatMessageDto);  // 메시지 저장
        return ResponseEntity.ok().build();  // 응답으로 HTTP 200 OK
    }

    @MessageMapping("/createChatRoom")
    public ResponseEntity<List<ChatMessageDto>> getMessages(@PathVariable Long chatRoomId) {
        System.out.println("controller task");
        List<ChatMessageDto> messages = userChatService.getMessages(chatRoomId);
        return ResponseEntity.ok(messages);  // 해당 채팅방의 메시지 목록 반환
    }


}
