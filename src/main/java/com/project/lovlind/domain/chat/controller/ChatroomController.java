package com.project.lovlind.domain.chat.controller;

import com.project.lovlind.conmon.requset.dto.CurrentUser;
import com.project.lovlind.domain.chat.controller.dto.request.PostChatDto;
import com.project.lovlind.domain.chat.entity.Chatroom;
import com.project.lovlind.domain.chat.service.ChatroomService;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

  /** 대화방 입장 */
  //  @PostMapping("/{id}/join")
  //  public ResponseEntity<Void> joinChatroom(@PathVariable Long id, CurrentUser user) {
  //    service.joinChatroom(id, user.getUserId());
  //
  //    return ResponseEntity.ok().build();
  //  }

  /** 채팅방 나가기 */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> leaveChatroom(@PathVariable Long id, CurrentUser user) {
    service.leaveChatroom(id, user.getUserId());

    return ResponseEntity.ok().build();
  }

  /** 채팅방 조회 */
  @GetMapping
  public ResponseEntity<List<Chatroom>> getChatrooms() {
    return ResponseEntity.ok(service.getChatrooms());
  }

  @PostMapping
  public ResponseEntity<Void> postChatroom(@RequestBody PostChatDto dto) {
    Long result = service.saveChatroom(dto);
    return ResponseEntity.created(URI.create(URL + "/" + result)).build();
  }
}
