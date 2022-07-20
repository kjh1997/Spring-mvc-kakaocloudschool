package com.kjh.websocket.controller;

import com.kjh.websocket.dto.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import java.net.Socket;

@RequiredArgsConstructor
@Controller
public class MessageController{
    private final SimpMessageSendingOperations messagingTemplate;

    // 채팅 리스트 화면
    @MessageMapping("/chat/message")
    public void message(ChatMessageDTO message) {
        if (ChatMessageDTO.MessageType.JOIN.equals(message.getType()))
            message.setMessage(message.getSender() + "님이 입장하셨습니다.");
        messagingTemplate.convertAndSend("/sub/chat/room/" + message.getRoomId(), message);
    }
}