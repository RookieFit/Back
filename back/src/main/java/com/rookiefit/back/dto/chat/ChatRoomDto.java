package com.rookiefit.back.dto.chat;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ChatRoomDto {
    private Long chatRoomId;
    private String chatRoomName;
    private String creatorUserId;
    private List<String> participantUserIds = new ArrayList<>();
}
