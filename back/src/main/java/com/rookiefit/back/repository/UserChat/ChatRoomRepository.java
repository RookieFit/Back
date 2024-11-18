package com.rookiefit.back.repository.UserChat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rookiefit.back.entity.UserChat.ChatRoomEntity;
import java.util.Optional;



@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoomEntity , Long>{
   Optional<ChatRoomEntity> findById(Long chatRoomId);
}
