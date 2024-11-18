package com.rookiefit.back.service.implement;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.UserChatDto.ChatMessageDto;
import com.rookiefit.back.dto.UserChatDto.ChatRoomDto;
import com.rookiefit.back.entity.UserEntity;
import com.rookiefit.back.entity.UserChat.ChatMessageEntity;
import com.rookiefit.back.entity.UserChat.ChatRoomEntity;
import com.rookiefit.back.repository.UserRepository;
import com.rookiefit.back.repository.UserChat.ChatMessageRepository;
import com.rookiefit.back.repository.UserChat.ChatRoomRepository;
import com.rookiefit.back.service.UserChatService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserChatServiceImplement implements UserChatService {

    private final UserRepository userRepository;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatRoomDto createChatRoom(ChatRoomDto dto) {
        ChatRoomEntity chatRoomEntity = new ChatRoomEntity();
        chatRoomEntity.setRoomName(dto.getRoomName());

        // 참가자들 처리 (userId로 참가자 조회)
        List<UserEntity> participants = userRepository.findAllById(dto.getParticipants());
        chatRoomEntity.setParticipants(participants);

        // 채팅방 저장
        ChatRoomEntity savedRoom = chatRoomRepository.save(chatRoomEntity);

        // 저장된 채팅방을 DTO로 변환하여 반환
        return ChatRoomDto.fromEntity(savedRoom);
    }

    @Override
    @Transactional
    public List<ChatMessageDto> getMessages(Long chatRoomId) {
        System.out.println("service task");
         List<ChatMessageEntity> messages = chatMessageRepository.findByChatRoom_Id(chatRoomId);
         if (messages.isEmpty()) {
            System.out.println("No messages found for chatRoomId: " + chatRoomId);
        }
        return messages.stream()
                .map(message -> new ChatMessageDto(
                    message.getSender().getUserId(),
                    message.getContent(), 
                    message.getChatRoom().getId()))
                .collect(Collectors.toList());
                
    }

    @Override
    public void sendMessage(ChatMessageDto dto) {
            // senderId로 UserEntity를 찾기
        UserEntity sender = userRepository.findByUserId(dto.getSenderId());
    
        // chatRoomId로 ChatRoomEntity를 찾기
        ChatRoomEntity chatRoom = chatRoomRepository.findById(dto.getChatRoomId())
            .orElseThrow(() -> new IllegalArgumentException("Chat room not found"));

        // 메시지 엔티티 생성
        ChatMessageEntity message = new ChatMessageEntity(sender, dto.getContent(), chatRoom);

        // 메시지 저장
        chatMessageRepository.save(message);
    }
    
}
