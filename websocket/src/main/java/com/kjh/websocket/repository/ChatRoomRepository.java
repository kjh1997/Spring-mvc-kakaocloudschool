package com.kjh.websocket.repository;

import com.kjh.websocket.dto.ChatRoomDTO;

import javax.annotation.PostConstruct;
import java.util.*;

public class ChatRoomRepository {

    private Map<String, ChatRoomDTO> chatRoomMap;
    @PostConstruct
    private void init() {
        chatRoomMap = new LinkedHashMap<>();
    }
    public List<ChatRoomDTO> findAllRoom() {
        // 채팅방 생성순서 최근 순으로 반환
        List chatRooms = new ArrayList<>(chatRoomMap.values());
        Collections.reverse(chatRooms);
        return chatRooms;
    }
    public ChatRoomDTO findRoomById(String id) {
        return chatRoomMap.get(id);
    }
    public ChatRoomDTO createChatRoom(String name) {
        ChatRoomDTO chatRoom = ChatRoomDTO.create(name);
        chatRoomMap.put(chatRoom.getRoomId(), chatRoom);
        return chatRoom;
    }
}
