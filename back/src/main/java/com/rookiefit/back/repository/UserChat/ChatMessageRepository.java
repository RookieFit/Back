package com.rookiefit.back.repository.UserChat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserChat.ChatMessageEntity;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessageEntity , Long>{
    List<ChatMessageEntity> findByChatRoom_Id(Long chatRoomId);
}
