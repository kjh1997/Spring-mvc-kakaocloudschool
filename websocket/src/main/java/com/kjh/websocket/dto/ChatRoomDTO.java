package com.kjh.websocket.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ChatRoomDTO {
    private String roomId;
    private String name;
    public static ChatRoomDTO create(String name) {
        ChatRoomDTO chatRoom = new ChatRoomDTO();
        chatRoom.roomId = UUID.randomUUID().toString();
        chatRoom.name = name;
        return chatRoom;
    }
}
