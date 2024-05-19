package com.project.lovlind.domain.chat.controller;

import com.project.lovlind.domain.chat.controller.dto.request.MessageDto;
import com.project.lovlind.domain.chat.controller.dto.response.SendMessageDto;
import com.project.lovlind.domain.chat.service.ChatroomWebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatWebsocketController {
  private final SimpMessageSendingOperations template;
  private final ChatroomWebSocketService service;

  @MessageMapping("/chat/{chatroomId}")
  public void sendChatroomMessage(
      MessageDto message,
      @DestinationVariable(value = "chatroomId") Long chatroomId,
      @Header("simpSessionId") String sessionId) {
    SendMessageDto sendMessage = service.saveMessage(message, chatroomId, sessionId);

    template.convertAndSend("/sub/chat/" + chatroomId, sendMessage);
  }
}
