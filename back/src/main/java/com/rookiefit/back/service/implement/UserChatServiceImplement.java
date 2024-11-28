package com.rookiefit.back.service.implement;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.rookiefit.back.dto.chat.ChatMessageDto;
import com.rookiefit.back.dto.chat.ChatRoomDto;
import com.rookiefit.back.entity.UserProfileEntity;
import com.rookiefit.back.entity.UserChat.UserChatMessageEntity;
import com.rookiefit.back.entity.UserChat.UserChatRoomEntity;
import com.rookiefit.back.repository.UserProfileRepository;
import com.rookiefit.back.repository.UserChat.UserChatRoomRepository;
import com.rookiefit.back.service.UserChatService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserChatServiceImplement implements UserChatService{

     private final UserChatRoomRepository userChatRoomRepository;
    private final UserProfileRepository userProfileRepository;
    private final SimpMessagingTemplate messagingTemplate;

    // 1대1 채팅방 생성
    public Long createChatRoom(ChatRoomDto chatRoomDto, String currentUserId) {
        UserChatRoomEntity chatRoomEntity = new UserChatRoomEntity();
        chatRoomEntity.setChatRoomName(chatRoomDto.getChatRoomName());

        // currentUserId를 채팅방에 추가
        UserProfileEntity currentUserProfile = userProfileRepository.findByUserAuthEntity_UserId(currentUserId);
        if (currentUserProfile != null) {
            chatRoomEntity.addParticipant(currentUserProfile);
            // 닉네임을 저장
            System.out.println("Added participant with nickname: " + currentUserProfile.getUserNickname());
        }

        // 다른 참가자들 추가 (chatRoomDto에서 받은 userId들)
        for (String userId : chatRoomDto.getParticipantUserIds()) {
            UserProfileEntity userProfile = userProfileRepository.findByUserAuthEntity_UserId(userId);
            if (userProfile != null) {
                chatRoomEntity.addParticipant(userProfile);
                // 참가자의 닉네임을 저장
                System.out.println("Added participant with nickname: " + userProfile.getUserNickname());
            }
        }

        // 채팅방 저장
        UserChatRoomEntity savedChatRoom = userChatRoomRepository.save(chatRoomEntity);
        System.out.println(savedChatRoom.getChatRoomId());

        // 생성된 채팅방 ID 반환
        return savedChatRoom.getChatRoomId();
    }

    // 메시지 보내기
    public void sendMessage(ChatMessageDto chatMessageDto) {
        // 채팅방 ID로 채팅방 찾기
        UserChatRoomEntity chatRoom = userChatRoomRepository.findById(chatMessageDto.getChatRoomId())
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        // 발신자 프로필 찾기
        UserProfileEntity sender = userProfileRepository.findByUserAuthEntity_UserId(chatMessageDto.getSenderUserId());
        if (sender == null) {
            throw new RuntimeException("Sender profile not found");
        }

        // 메시지 엔티티 생성
        UserChatMessageEntity message = new UserChatMessageEntity(
                chatMessageDto.getContent(),
                chatRoom,
                sender
        );

        // 메시지 저장
        chatRoom.getMessages().add(message);
        userChatRoomRepository.save(chatRoom);

        // 메시지를 해당 채팅방에 참여한 사용자들에게 전송
        messagingTemplate.convertAndSend("/topic/chatroom/" + chatMessageDto.getChatRoomId(), chatMessageDto);
    }

    // 채팅방 삭제
    public void deleteChatRoom(Long chatRoomId) {
        UserChatRoomEntity chatRoom = userChatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new RuntimeException("Chat room not found"));

        userChatRoomRepository.delete(chatRoom);
    }
}
