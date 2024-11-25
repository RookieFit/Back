package com.rookiefit.back.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rookiefit.back.dto.UserChatDto.ChatMessageDto;
import com.rookiefit.back.dto.UserChatDto.ChatRoomDto;
import com.rookiefit.back.service.UserChatService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/user/chat")
@RequiredArgsConstructor
@Slf4j
public class UserChatController {
    private final UserChatService userChatService;
    private static final Logger logger = LoggerFactory.getLogger(UserChatController.class);

    // 채팅방 생성
    @PostMapping("/create")
    public ResponseEntity<ChatRoomDto> createChatRoom(@RequestBody ChatRoomDto chatRoomDto) {
        ChatRoomDto createdChatRoom = userChatService.createChatRoom(chatRoomDto);
        return ResponseEntity.ok(createdChatRoom); // 생성된 채팅방 반환
    }

    // 채팅방 메시지 조회
    @GetMapping("/{chatRoomId}/messages")
    public ResponseEntity<List<ChatMessageDto>> getMessages(@PathVariable Long chatRoomId) {
        logger.debug("chatRoomId: {}", chatRoomId);
        System.out.println("controller task");
        List<ChatMessageDto> messages = userChatService.getMessages(chatRoomId);
        return ResponseEntity.ok(messages); // 해당 채팅방의 메시지 목록 반환
    }

    // 메시지 전송
    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody ChatMessageDto chatMessageDto) {
        userChatService.sendMessage(chatMessageDto); // 메시지 저장
        return ResponseEntity.ok().build(); // 응답으로 HTTP 200 OK
    }
}
