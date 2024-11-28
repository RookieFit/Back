package com.rookiefit.back.repository.UserChat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserChat.UserChatRoomEntity;

@Repository
public interface UserChatRoomRepository extends JpaRepository<UserChatRoomEntity, Long>{
    
}
