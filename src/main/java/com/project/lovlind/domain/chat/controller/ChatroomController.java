package com.project.lovlind.domain.chat.controller;

import com.project.lovlind.domain.chat.controller.dto.request.PostChatDto;
import com.project.lovlind.domain.chat.service.ChatroomService;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatroom")
@RequiredArgsConstructor
public class ChatroomController {
  private final ChatroomService service;
  private static final String URL = "/api/chatroom";

  @PostMapping
  public ResponseEntity<Void> postChatroom(@RequestBody PostChatDto dto) {
    Long result = service.saveChatroom(dto);
    return ResponseEntity.created(URI.create(URL + "/" + result)).build();
  }
}
